import React, { useContext, useState } from "react";
import { Typography, List, Divider } from "antd";
import { Layout, Menu, Row, Col, Input, Button, Radio, Spin, message } from "antd";
import { EnvironmentOutlined, UserOutlined, CreditCardOutlined, QrcodeOutlined } from "@ant-design/icons";
import { FaBus } from "react-icons/fa";
import axios from "axios";
import { useLocation, useNavigate } from "react-router-dom";
import DataContext from "./context/DataContext";
import '../styles/Payment.css'
import Footer from "./Footer";
import Header from "./Header";



const { Title, Text } = Typography;
const { Content } = Layout;

const Payment = () => {
    const location = useLocation();
    const [seatInfo, setSeatInfo] = useState(location.state?.selectedSeats || null);
    const [paymentMethod, setPaymentMethod] = useState("upi");
    const [loading, setLoading] = useState(false);
    const [inputData, setInputData] = useState({});
    const navigate = useNavigate();
    const { bookingDetails, token } = useContext(DataContext);

    if (!bookingDetails) {
        return <div>Loading...</div>;
    }

    const { bookingId, bus, journeyDate } = bookingDetails;

    const handleInputChange = (field, value) => {
        setInputData({ ...inputData, [field]: value });
    };

    const handlePayment = () => {
        setLoading(true);
        console.log(total)
        axios.post(`http://localhost:8084/payment/process/${bookingId}/${parseFloat(total)}`,{}, {
            headers: { Authorization: `Bearer ${token}` }
        })
            .then((response) => {
                console.log(response)
                message.success("Payment successful!");
                    setTimeout(() => {
                        navigate("/confirmation");
                    }, 3000);
                navigate("/confirmation");
            })
            .catch((error) => {
                message.error("An error occurred while processing payment.",error);
            })
            .finally(() => {
                setLoading(false);
            });
    };

    const tax = seatInfo.length * bus.pricePerSeat * 0.18;
    const total = seatInfo.length * bus.pricePerSeat * 1.18;

    return (
        <>
        <Header/>
        <Layout>
            <Content style={{padding:"20px 0"}}>
                <Row>
                    <Col span={16}>
                        <div style={{ padding: "20px", background: "#fff", height:"100%", display:"flex", gap:"40px" }}>
                        <Menu
                            mode="vertical"
                            selectedKeys={[paymentMethod]}
                            onClick={(e) => setPaymentMethod(e.key)}
                            style={{marginRight:"20px"}}
                        >
                            <Menu.Item className="payment-method-item" key="upi">
                                UPI Options
                            </Menu.Item>
                            <Menu.Item className="payment-method-item" key="card">
                                Credit/Debit/ATM Card
                            </Menu.Item>
                            <Menu.Item className="payment-method-item" key="netbanking">
                                Net Banking
                            </Menu.Item>
                            <Menu.Item className="payment-method-item" key="googlepay">
                                Google Pay
                            </Menu.Item>
                        </Menu>

                            <div  className="payment-box">
                                {paymentMethod === "upi" && (
                                    <div>
                                        <Title level={4} style={{ marginBottom: "10px",marginTop:"2px" }}>UPI Options</Title>
                                        <Row>
                                            <Col span={12} style={{ textAlign: "center",marginBottom:"10px" }}>
                                                <QrcodeOutlined style={{ fontSize: "64px" }} />
                                                <div>Scan and Pay</div>
                                            </Col>
                                            <Col span={20}>
                                                <Input
                                                    placeholder="Enter UPI ID"
                                                    onChange={(e) => handleInputChange("upiId", e.target.value)}
                                                />
                                            </Col>
                                        </Row>
                                    </div>
                                )}

                                {paymentMethod === "card" && (
                                    <div>
                                        <Title level={4} style={{ marginBottom: "10px",marginTop:"2px" }}>CARDS</Title>
                                        <Input
                                            placeholder="Card Number"
                                            style={{ marginBottom: "10px" }}
                                            onChange={(e) => handleInputChange("cardNumber", e.target.value)}
                                        />
                                        <Input
                                            placeholder="Name on Card"
                                            style={{ marginBottom: "10px" }}
                                            onChange={(e) => handleInputChange("cardName", e.target.value)}
                                        />
                                        <Input
                                            placeholder="Expiry Month & Year"
                                            style={{ marginBottom: "10px" }}
                                            onChange={(e) => handleInputChange("expiry", e.target.value)}
                                        />
                                        <Input.Password
                                            placeholder="Enter CVV"
                                            style={{ marginBottom: "10px" }}
                                            onChange={(e) => handleInputChange("cvv", e.target.value)}
                                        />
                                    </div>
                                )}

                                {paymentMethod === "netbanking" && (
                                    <div>
                                        <Title level={4} style={{ marginBottom: "10px",marginTop:"2px" }}>Net Banking</Title>
                                        <Input.Search
                                            placeholder="Search your bank"
                                            enterButton
                                            onSearch={(value) => handleInputChange("bank", value)}
                                        />
                                        <Radio.Group
                                            style={{ marginTop: "20px" }}
                                            onChange={(e) => handleInputChange("selectedBank", e.target.value)}
                                        >
                                            <Radio value="bank1">Bank 1</Radio>
                                            <Radio value="bank2">Bank 2</Radio>
                                            <Radio value="bank3">Bank 3</Radio>
                                        </Radio.Group>
                                    </div>
                                )}

                                {paymentMethod === "googlepay" && (
                                    <div>
                                        <Title level={4} style={{ marginBottom: "10px",marginTop:"2px" }}>Google Pay</Title>
                                        <Input
                                            placeholder="Enter UPI ID"
                                            onChange={(e) => handleInputChange("gpayUpiId", e.target.value)}
                                        />
                                        <div style={{marginTop:"10px"}}>A UPI ID typically looks like this: <b>example@upi</b> or <b>user@bankname</b>.</div>
                                        
                                    </div>
                                )}
                                <div className="payment-box-bottom">
                                <strong>Total Amount:</strong> ₹{total.toFixed(2)}
                                    <Button
                                        type="primary"
                                        onClick={handlePayment}
                                    >
                                        Verify & Pay
                                    </Button>    
                                </div>
                            </div>
                        </div>
                    </Col>
                    <Col span={8}>
                        <div style={{ padding: "10px 30px", background: "#fff", maxWidth: "600px", margin: "0 auto", marginLeft:"3px"}}>
                            <Title level={4} style={{ textAlign: "center", marginBottom: "10px",marginTop:"2px" }}>Booking Summary</Title>
                            <div>
                                <Text strong style={{ fontSize: "16px" }}><FaBus style={{ marginRight: "8px"}} />{bus.busName}</Text>
                                <div style={{ fontSize: "14px", color: "#555", marginTop: "5px" }}>{bus.busType}</div>
                                <div style={{ fontSize: "14px", marginTop: "10px" }}>
                                    <Text type="primary" strong>{bus.route?.routeFrom} ⇌ {bus.route?.routeTo}</Text><br />
                                    <Text>{journeyDate}</Text>
                                </div>
                            </div>
                            <Divider style={{margin:"5px 0"}} />
                            <div>
                                <Title level={5} style={{ marginBottom: "10px", marginTop:"10px" }}>Pick Up</Title>
                                <Text><EnvironmentOutlined style={{ marginRight: "8px" }} />{bus.route?.boardingPoint} </Text> <Text>at {bus.departure}</Text>
                                <Title level={5} style={{ margin: "10px 0" }}>Drop Off</Title>
                                <Text><EnvironmentOutlined style={{ marginRight: "8px" }} />{bus.route?.dropingPoint} </Text> <Text>at {bus.arrival}</Text>
                            </div>
                            <Divider style={{margin:"5px 0"}} />
                            <div>
                                <Title level={5} style={{ marginBottom: "10px",marginTop:"10px" }}>Seats</Title>
                                <List
                                    size="small"
                                    dataSource={seatInfo.map(seat =>` Seat: ${seat}`)}  // Assuming seatInfo is an array
                                    renderItem={(item) => <List.Item style={{padding:"0 20px"}}>{item}</List.Item>}
                                />
                            </div>
                            <Divider style={{margin:"5px 0"}} />
                            <div>
                                <Title level={5} style={{ marginBottom: "10px",marginTop:"10px"  }}>Ticket Summary</Title>
                                <Text>Base Fare: ₹{(seatInfo.length * bus.pricePerSeat).toFixed(2)}</Text><br />
                                <Text>Tax: ₹{tax.toFixed(2)}</Text><br />
                                <Text strong style={{ fontSize: "16px", marginTop: "10px", display: "block" }}>
                                    <CreditCardOutlined style={{ marginRight: "8px" }} />Total: ₹{total.toFixed(2)}
                                </Text>
                            </div>
                        </div>
                    </Col>
                </Row>
            </Content>
            {loading && (
                <div style={{ position: "fixed", top: "50%", left: "50%", transform: "translate(-50%, -50%)" }}>
                    <Spin size="large" />
                </div>
            )}
        </Layout>
        <Footer/>
        </>
    );
};

export default Payment;