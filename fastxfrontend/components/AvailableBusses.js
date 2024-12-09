import React from 'react';
import '../styles/AvailableBuses.css';
import { Card, Button, Tag, Row, Col,Typography, message } from "antd";
import { EnvironmentOutlined, CalendarOutlined, ClockCircleOutlined, UserOutlined } from "@ant-design/icons";
import { useContext } from 'react';
import DataContext from './context/DataContext';
import { useNavigate } from 'react-router-dom';



const { Title} = Typography;


const AvailableBusses=()=> {
  const nav =useNavigate();
  const {buses,userDetails,token} =useContext(DataContext);

  const showbuses=(bus)=>{
    if(token===""){
      message.error("Please login")
    }
    else if(userDetails.role==="ADMIN"){
      message.error("Admin has no access")
    }
    else{
      nav("/busbooking",{state:{bus}})
    }
  }
  
  return (
    <section className="section">
      <Title level={2} className="title">Available Buses</Title>
      <Row gutter={[16, 16]} style={{marginLeft:"10px"}}>
        {buses.length!==0?
        buses.map((bus) => (
          <Col xs={24} sm={12} md={8} key={bus.id}>
            <Card
      className="bus-card"
      title={
        <div style={{ display: "flex", justifyContent: "space-between", alignItems: "center" }}>
          <span>{bus.busName}</span>
          <Tag color="blue">{bus.busType}</Tag>
        </div>
      }
      extra={<Button onClick={()=>showbuses(bus)} type="primary">View Details</Button>}
      style={{ marginBottom: "20px", borderRadius: "8px", overflow: "hidden" }}
    >
      <Row gutter={[16, 0]}>
        <Col span={24}>
          <p className="bus-routes">
            <EnvironmentOutlined /> {bus.route.routeFrom} → {bus.route.routeTo}
          </p>
        </Col>

        <Col span={12}>
          <p>
            <ClockCircleOutlined /> Arrival: {bus.arrival}
          </p>
        </Col>
        <Col span={12}>
          <p>
            <ClockCircleOutlined /> Departure: {bus.departure}
          </p>
        </Col>

        <Col span={12}>
          <p>
            <CalendarOutlined /> Date: {bus.date}
          </p>
        </Col>
        <Col span={12}>
          <p>
            <UserOutlined />Seats: {bus.availableSeats}/{bus.totalSeats}
          </p>
        </Col>
      </Row>

      <Row justify="space-between" align="middle" style={{borderTop: "1px solid lightgray", marginTop: "10px", paddingTop: "7px"}}>
      <Col span={15}>
          <p>
            Points: {bus.route.boardingPoint} → {bus.route.dropingPoint}
          </p>
        </Col>
        <Col>
          <Tag color="green" style={{fontWeight:"500", backgroundColor:"rgba(9, 226, 9, 0.19)"}}>Price: ₹{bus.pricePerSeat}/seat</Tag>
        </Col>
      </Row>
    </Card>
          </Col>
        )):null}
      </Row>
    </section>
  );
}

export default AvailableBusses;

