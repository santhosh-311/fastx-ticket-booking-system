import React, { useContext, useEffect, useState } from "react";
import { Modal, Descriptions, Button, Tag, Divider, message } from "antd";
import { UserOutlined, CalendarOutlined, CarOutlined } from "@ant-design/icons";
import DataContext from "./context/DataContext";
import axios from "axios";

const ViewTicket = ({ bookingId,refreshBookings }) => {
  const [isModalVisible, setIsModalVisible] = useState(false);
  const [details, setDetails] = useState(null);
  const { userDetails } = useContext(DataContext);
  const BASE_URL = "http://localhost:8084";

  // Function to fetch booking details
  const fetchBookingDetails = async () => {
    try {
      const response = await axios.get(`${BASE_URL}/booking/getbooking/${bookingId}`, {
        headers: { Authorization: `Bearer ${userDetails.jwt}` },
      });
      console.log("Fetched successfully", response.data);
      setDetails(response.data);
    } catch (error) {
      console.error("Error fetching details", error);
      message.error("Error fetching details");
    }
  };

  useEffect(() => {
    if (isModalVisible) fetchBookingDetails();
  }, [isModalVisible]);

  const handleCancel = () => {
    setIsModalVisible(false);
  };

  const cancelTicket = async (id) => {
    try {
      Modal.confirm({
        title: "Are you sure you want to cancel Booking?",
        onOk: async () => {
          await axios.put(`${BASE_URL}/booking/user/cancel/${id}`, {}, {
            headers: { Authorization: `Bearer ${userDetails.jwt}` },
          });
          message.success("Ticket cancelled successfully!");
          message.warning("Refund pending");
          await fetchBookingDetails(); // Refresh data
          refreshBookings();
        },
      });
    } catch (error) {
      console.error("Failed to cancel ticket", error);
      message.error("Failed to cancel ticket.");
    }
  };

  const refundProcess = async (booking, payment) => {
    try {
      Modal.confirm({
        title: "Confirm Refund?",
        onOk: async () => {
          await axios.put(`${BASE_URL}/payment/refund/${payment}/${booking}`, {}, {
            headers: { Authorization: `Bearer ${userDetails.jwt}` },
          });
          message.success("Refund successful");
          await fetchBookingDetails(); // Refresh data
          refreshBookings();
        },
      });
    } catch (error) {
      console.error("Failed to process refund", error);
      message.error("Failed to process refund.");
    }
  };

  const cancelBooking = async (id) => {
    try {
      Modal.confirm({
        title: "Confirm Cancellation?",
        onOk: async () => {
          await axios.put(`${BASE_URL}/booking/op/cancel/${userDetails.userName}/${id}`, {}, {
            headers: { Authorization: `Bearer ${userDetails.jwt}` },
          });
          message.success("Cancellation successful");
          await fetchBookingDetails(); // Refresh data
          refreshBookings();
        },
      });
    } catch (error) {
      console.error("Cancelling ERROR:", error);
      message.error("Cancellation Failed");
    }
  };

  return (
    <>
      <Button type="primary" onClick={() => setIsModalVisible(true)}>
        View Booking
      </Button>
      {details && (
        <Modal
          title={
            <div style={{ display: "flex", alignItems: "center", gap: "10px" }}>
              <CarOutlined />
              Booking Details
            </div>
          }
          open={isModalVisible}
          onCancel={handleCancel}
          centered
          footer={null}
        >
          <Descriptions style={{ marginBottom: "30px" }} bordered column={1}>
            <Descriptions.Item label="Booked Seats">
              <Tag color="green">{details?.seatInfo || "N/A"}</Tag>
            </Descriptions.Item>
            <Descriptions.Item label="Bus Name">{details?.bus.busName || "N/A"}</Descriptions.Item>
            <Descriptions.Item label="Route">
              <strong>{details?.bus.route.routeFrom || "N/A"}</strong> →{" "}
              <strong>{details?.bus.route.routeTo || "N/A"}</strong>
            </Descriptions.Item>
            <Descriptions.Item label="Timing">
              <CalendarOutlined style={{ marginRight: "5px" }} />
              Arrival: <strong>{details?.bus.arrival || "N/A"}</strong>, Departure:{" "}
              <strong>{details?.bus.departure || "N/A"}</strong>
            </Descriptions.Item>
            <Descriptions.Item label="Amenities">
              {details?.bus.amenities
                ? details?.bus.amenities.split(", ").map((item, index) => (
                    <Tag color="blue" key={index}>
                      {item}
                    </Tag>
                  ))
                : "N/A"}
            </Descriptions.Item>
            <Descriptions.Item label="Bus Number">{details?.bus.busNumber || "N/A"}</Descriptions.Item>
            <Descriptions.Item label="Bus Type">
              <Tag color="purple">{details?.bus.busType || "N/A"}</Tag>
            </Descriptions.Item>
            <Descriptions.Item label="Operator Number">
              <UserOutlined style={{ marginRight: "5px" }} />
              {details?.bus.user.number || "N/A"}
            </Descriptions.Item>
            <Descriptions.Item label="Total Amount (Without Tax)">
              ₹
              {details?.bus.pricePerSeat
                ? (details?.bus.pricePerSeat * (details?.seatInfo.split(",").length || 0)).toFixed(2)
                : "N/A"}
            </Descriptions.Item>
            <Descriptions.Item label="Tax Amount">
              ₹
              {details?.bus.pricePerSeat
                ? ((details?.bus.pricePerSeat * (details?.seatInfo.split(",").length || 0)) * 0.1).toFixed(2)
                : "N/A"}
            </Descriptions.Item>
            <Descriptions.Item label="Booked Date">{details?.bookingDate || "N/A"}</Descriptions.Item>
            <Descriptions.Item label="Journey Date">{details?.bus.date || "N/A"}</Descriptions.Item>
          </Descriptions>
          {userDetails.role === "USER" ? (
            <Button
              disabled={details?.bookingStatus !== "confirmed"}
              key="cancel ticket"
              onClick={() => cancelTicket(details?.bookingId)}
              type="primary"
              style={{ backgroundColor: "#f44336" }}
            >
              Cancel Ticket
            </Button>
          ) : userDetails.role === "OPERATOR" ? (
            details?.bookingStatus !== "Refund Pending" && details?.bookingStatus !== "Refunded" ? (
              <Button
                disabled={details?.bookingStatus === "Cancelled"}
                type="primary"
                onClick={() => cancelBooking(details?.bookingId)}
              >
                Cancel Booking
              </Button>
            ) : (
              <Button
                disabled={details.bookingStatus === "Refunded"}
                onClick={() => refundProcess(details?.bookingId, details?.payment.paymentId)}
                key="refund"
                type="primary"
              >
                {details.bookingStatus === "Refunded" ? "Refunded" : "Refund"}
              </Button>
            )
          ) : null}
          <Button key="cancel" onClick={handleCancel} style={{ marginLeft: "10px" }}>
            Close
          </Button>

          <Divider />
          <p style={{ textAlign: "center", color: "gray" }}>
            For assistance, contact our customer support.
          </p>
        </Modal>
      )}
    </>
  );
};

export default ViewTicket;
