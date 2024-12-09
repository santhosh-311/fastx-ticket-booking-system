import Header from "./Header"
import { Modal, Card, Button, Tag, Row, Col } from "antd";
import { EnvironmentOutlined, CalendarOutlined, ClockCircleOutlined, UserOutlined, CheckCircleOutlined, FieldTimeOutlined, CloseCircleOutlined } from "@ant-design/icons";
import { useContext, useEffect, useState } from "react";
import axios from "axios";
import DataContext from "./context/DataContext";
import '../styles/Booking.css';
import Footer from "./Footer";
import { useLocation } from "react-router-dom";
import ViewTicket from "./ViewTicket";

const ManageBookings = () => {

    const bus = useLocation().state.bus;
    const { userDetails, token } = useContext(DataContext);
    const [bookings, setBookings] = useState([]);
    const [activeTab, setActiveTab] = useState("current");
    const [isModalOpen, setIsModalOpen] = useState(false);

    useEffect(() => {
        axios.get(`http://localhost:8084/booking/op/viewbookings/${userDetails.userName}/${bus.busId}`, {
            headers: { Authorization: `Bearer ${token}` },
        })
        .then((res) => {
            console.log(res.data);
            setBookings(res.data);
            console.log("Booking details loaded doneeee...");
        })
        .catch((err) => {
            console.error("Bookings Error: ", err);
        });
    }, []);

    const refreshBookings = async () => {
        try {
            const response = await axios.get(
                `http://localhost:8084/booking/op/viewbookings/${userDetails.userName}/${bus.busId}`,
                { headers: { Authorization: `Bearer ${token}` } }
            );
            setBookings(response.data);
            console.log("Bookings refreshed.");
        } catch (err) {
            console.error("Failed to refresh bookings: ", err);
        }
    };

    const filterBookings = (type) => {
        const now = new Date();
        if (type === "current") {
            return bookings.filter((b) => new Date(b.journeyDate) >= now );
        } else if (type === "past") {
            return bookings.filter((b) => new Date(b.journeyDate) < now && b.journeyDate !== null);
        }
    };

    const currentBookings = filterBookings("current");
    const pastBookings = filterBookings("past");

    const showModal = () => {
        setIsModalOpen(true);
    };

    const handleOk = () => {
        setIsModalOpen(false);
    };

    const handleCancel = () => {
        setIsModalOpen(false);
    };

    return (
        <>
            <Header />
            <section className="section sec">
                <div className="top-btn-container">
                    <button
                        className={`top-btn ${activeTab === "current" ? "active" : "inactive"}`}
                        onClick={() => setActiveTab("current")}
                    >
                        Current Bookings
                    </button>
                    <button
                        className={`top-btn ${activeTab === "past" ? "active" : "inactive"}`}
                        onClick={() => setActiveTab("past")}
                    >
                        Past Bookings
                    </button>
                </div>

                <Row gutter={[16, 16]} style={{ margin: "30px 50px", padding: "20px 0", height: "400px", overflowY: "auto" }}>
                    {activeTab === "current" ? currentBookings.length !== 0 ?
                        currentBookings.map((booking) => (
                            <Col xs={24} sm={12} md={8} key={booking.bookingId}>
                                <Card
                                    className="booking-card"
                                    title={
                                        <div style={{ display: "flex", justifyContent: "space-between", alignItems: "center" }}>
                                            <span>{booking.bus.busName}</span>
                                            <Tag color="blue">{booking.bus.busType}</Tag>
                                        </div>
                                    }
                                    extra={<ViewTicket bookingId={booking.bookingId} refreshBookings={refreshBookings} />}
                                    style={{ marginBottom: "20px", borderRadius: "8px", overflow: "hidden" }}
                                >
                                    <Row gutter={[16, 0]} style={{ borderBottom: "2px solid lightgray", paddingBottom: "10px" }}>
                                        <Col span={24}>
                                            <p className="bus-routes">
                                                <EnvironmentOutlined /> {booking.bus.route.routeFrom} → {booking.bus.route.routeTo}
                                            </p>
                                        </Col>

                                        <Col span={12}>
                                            <p>
                                                <ClockCircleOutlined /> Arrival: {booking.bus.arrival}
                                            </p>
                                        </Col>
                                        <Col span={12}>
                                            <p>
                                                <ClockCircleOutlined /> Departure: {booking.bus.departure}
                                            </p>
                                        </Col>

                                        <Col span={12}>
                                            <p>
                                                <CalendarOutlined /> Journey Date: {booking.bus.date}
                                            </p>
                                        </Col>
                                        <Col span={12}>
                                            <p>
                                                <UserOutlined />Seats: {booking.seatInfo}
                                            </p>
                                        </Col>
                                        <Col span={15}>
                                            <p>
                                                Points: {booking.bus.route.boardingPoint} → {booking.bus.route.dropingPoint}
                                            </p>
                                        </Col>
                                    </Row>

                                    <Row justify="space-between" align="middle" style={{ margin: "0 20px", paddingTop: "7px" }}>
                                        <Col>
                                            {booking.bookingStatus === "confirmed" ? <CheckCircleOutlined className="check-icon" /> : booking.bookingStatus === "Refunded" ? <CloseCircleOutlined className="cancel-icon" /> : <FieldTimeOutlined className="pending-icon" />}
                                        </Col>
                                        <Col>
                                            <Tag color="green" style={{ fontWeight: "500", backgroundColor: "rgba(9, 226, 9, 0.19)" }}>Amount: ₹{parseInt(booking.bus.pricePerSeat) * (booking.seatInfo.split(",").length)}</Tag>
                                        </Col>
                                    </Row>
                                </Card>
                            </Col>
                        )) : <p>No Active Bookings Available</p>
                        : pastBookings.length !== 0 ?
                            pastBookings.map((booking) => (
                                <Col xs={24} sm={12} md={8} key={booking.bookingId}>
                                    <Card
                                        className="booking-card"
                                        title={
                                            <div style={{ display: "flex", justifyContent: "space-between", alignItems: "center" }}>
                                                <span>{booking.bus.busName}</span>
                                                <Tag color="blue">{booking.bus.busType}</Tag>
                                            </div>
                                        }
                                        extra={<ViewTicket bookingId={booking.bookingId} refreshBookings={refreshBookings} />}
                                        style={{ marginBottom: "20px", borderRadius: "8px", overflow: "hidden" }}
                                    >
                                        <Row gutter={[16, 0]} style={{ borderBottom: "2px solid lightgray", paddingBottom: "10px" }}>
                                            <Col span={24}>
                                                <p className="bus-routes">
                                                    <EnvironmentOutlined /> {booking.bus.route.routeFrom} → {booking.bus.route.routeTo}
                                                </p>
                                            </Col>

                                            <Col span={12}>
                                                <p>
                                                    <ClockCircleOutlined /> Arrival: {booking.bus.arrival}
                                                </p>
                                            </Col>
                                            <Col span={12}>
                                                <p>
                                                    <ClockCircleOutlined /> Departure: {booking.bus.departure}
                                                </p>
                                            </Col>

                                            <Col span={12}>
                                                <p>
                                                    <CalendarOutlined /> Journey Date: {booking.bus.date}
                                                </p>
                                            </Col>
                                            <Col span={12}>
                                                <p>
                                                    <UserOutlined />Seats: {booking.seatInfo}
                                                </p>
                                            </Col>
                                            <Col span={15}>
                                                <p>
                                                    Points: {booking.bus.route.boardingPoint} → {booking.bus.route.dropingPoint}
                                                </p>
                                            </Col>
                                        </Row>

                                        <Row justify="space-between" align="middle" style={{ margin: "0 20px", paddingTop: "7px" }}>
                                            <Col>
                                                {booking.bookingStatus === "confirmed" ? <CheckCircleOutlined className="check-icon" /> : booking.bookingStatus === "payment pending" ? <FieldTimeOutlined className="pending-icon" /> : <CloseCircleOutlined className="cancel-icon" />}
                                            </Col>
                                            <Col>
                                                <Tag color="green" style={{ fontWeight: "500", backgroundColor: "rgba(9, 226, 9, 0.19)" }}>Amount: ₹{parseInt(booking.bus.pricePerSeat) * (booking.seatInfo.split(",").length)}</Tag>
                                            </Col>
                                        </Row>
                                    </Card>
                                </Col>
                            )) : <p>No booking history</p>}
                </Row>
                <Modal
                    title={"Booking Details"}
                    open={isModalOpen}
                    onOk={handleOk}
                    onCancel={handleCancel}
                    footer={null}
                    width={400}
                >
                    <p>Booking Details</p>
                </Modal>
            </section>
            <Footer />
        </>
    );
}

export default ManageBookings;
