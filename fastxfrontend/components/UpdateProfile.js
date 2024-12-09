import React, { useState, useEffect, useContext } from "react";
import { Form, Input, Button, message } from "antd";
import axios from "axios";
import Header from "./Header";
import DataContext from "./context/DataContext";
import '../styles/UpdateProfile.css'
import Footer from "./Footer";

const BASE_URL = "http://localhost:8084/";

const UpdateProfile = () => {
  const { userDetails, token } = useContext(DataContext);
  const [loading, setLoading] = useState(false);
  const [user, setUser] = useState(null);
  const [activeTab, setActiveTab] = useState("details");


  useEffect(() => {
    const fetchUserDetails = async () => {
      try {
        setLoading(true);
        const response = await axios.get(`${BASE_URL}user/getuser/${userDetails.userName}`, {
          headers: { Authorization: `Bearer ${token}`},
        });
        setUser(response.data);
      } catch (error) {
        message.error("Failed to fetch user details.");
      } finally {
        setLoading(false);
      }
    };

    fetchUserDetails();
  }, [userDetails.userName, token]);

  const handleUpdateDetails = async (values) => {
    try {
      setLoading(true);
      const response = await axios.put(
        `${BASE_URL}user/update/${userDetails.userName}`,
        values,
        {
          headers: { Authorization: `Bearer ${token}` },
        }
      );
      setUser(response.data);
      message.success("User details updated successfully!");
    } catch (error) {
      message.error("Failed to update user details.");
    } finally {
      setLoading(false);
    }
  };

  const handleChangePassword = async (values) => {
    try {
      setLoading(true);
      await axios.put(
        `${BASE_URL}user/updatepwd/${userDetails.userName}/${values.newPassword}`,
        {},
        {
          headers: { Authorization: `Bearer ${token} `},
        }
      );
      message.success("Password updated successfully!");
    } catch (error) {
      message.error("Failed to update password.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <>
      <Header />
      <div style={{ padding: "20px", maxWidth: "900px", margin: "10px 60px 20px" }}>
      <div className="top-btn-container">
                <button className={`top-btn ${activeTab === "details" ? "active" : "inactive"}`}
                    onClick={() => setActiveTab("details")}>
                    Update Details
                </button>
                <button className={`top-btn ${activeTab === "password" ? "active" : "inactive"}`}
                    onClick={() => setActiveTab("password")}>
                    Change Password
                </button>
            </div>
        { activeTab==="details"? user ? (
          <Form
            layout="vertical"
            initialValues={user}
            onFinish={handleUpdateDetails}
            style={{ margin: "20px" }}
          >
            <div className="form-container">
              <div className="form-left">
            <Form.Item
              label="Name"
              name="name"
              rules={[{ required: true, message: "Name is required." }]}
            >
              <Input placeholder="Enter your name" />
            </Form.Item>
            <Form.Item
              label="Email"
              name="email"
              rules={[{ required: true, message: "Email is required." }]}
            >
              <Input placeholder="Enter your email" disabled />
            </Form.Item>
            <Form.Item
              label="Phone"
              name="number"
              rules={[
                { required: true, message: "Phone number is required." },
                { pattern: /^[0-9]{10}$/, message: "Enter a valid 10-digit phone number." },
              ]}
            >
              <Input placeholder="Enter your phone number" />
            </Form.Item>
            <Form.Item
              label="Date of Birth"
              name="dob"
              rules={[{ required: true, message: "Date of birth is required." }]}
            >
              <Input placeholder="Enter your date of birth" />
            </Form.Item>
            <Form.Item
              label="Gender"
              name="gender"
              rules={[{ required: true, message: "Gender is required." }]}
            >
              <Input placeholder="Enter your gender" />
            </Form.Item>
            </div>
            <div className="form-right">
            <Form.Item
              label="Address"
              name="address"
              rules={[{ required: true, message: "Address is required." }]}
            >
              <Input placeholder="Enter your address" />
            </Form.Item>
            <Form.Item
              label="City"
              name="city"
              rules={[{ required: true, message: "City is required." }]}
            >
              <Input placeholder="Enter your city" />
            </Form.Item>
            <Form.Item
              label="State"
              name="state"
              rules={[{ required: true, message: "State is required." }]}
            >
              <Input placeholder="Enter your state" />
            </Form.Item>
            <Form.Item
              label="Country"
              name="country"
              rules={[{ required: true, message: "Country is required." }]}
            >
              <Input placeholder="Enter your country" />
            </Form.Item>

            {/* Additional Fields for Operator */}
            {userDetails.role === "OPERATOR" && (
              <>
                <Form.Item
                  label="Aadhar"
                  name="aadhar"
                  rules={[{ required: true, message: "Aadhar is required." }]}
                >
                  <Input placeholder="Enter your Aadhar number" disabled />
                </Form.Item>
                <Form.Item
                  label="PAN"
                  name="pan"
                  rules={[{ required: true, message: "PAN is required." }]}
                >
                  <Input placeholder="Enter your PAN number" disabled />
                </Form.Item>
              </>
            )}
            </div>
            </div>
            <Button type="primary" htmlType="submit" loading={loading}>
                {console.log(user)}
              Update Details
            </Button>
          </Form>
        ) : (
          <p>Loading user details...</p>
        )
        :
        <Form layout="vertical" 
            onFinish={handleChangePassword}
            style={{ margin: "20px" }}
        >
          <Form.Item
            label="New Password"
            name="newPassword"
            rules={[{ required: true, message: "Password is required." }]}
          >
            <Input.Password placeholder="Enter new password" />
          </Form.Item>
          <Button type="primary" htmlType="submit" loading={loading}>
            Change Password
          </Button>
        </Form>
      }
      </div>
      <Footer/>
    </>
  );
};

export default UpdateProfile;