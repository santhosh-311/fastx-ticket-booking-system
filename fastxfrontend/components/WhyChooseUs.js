import React from 'react';
import { Row, Col, Typography } from 'antd';
import { CheckCircleOutlined } from '@ant-design/icons';
import '../styles/WhyChooseUs.css';

const { Title, Text } = Typography;

const reasons = [
  'Seamless Booking Experience',
  'Instant Ticket Confirmation',
  'Real-Time Availability',
  'Flexible Routes',
];

function WhyChooseUs() {
  return (
    <section className="section">
      <Title level={2} className="title">Why Choose Us?</Title>
      <Row gutter={[16, 16]}>
        {reasons.map((reason, index) => (
          <Col xs={24} sm={12} key={index}>
            <div className='reason-card'>
              <div className="reason">
                <CheckCircleOutlined className="icon" />
                <Text className="reasonText">{reason}</Text>
              </div>
            </div>
          </Col>
        ))}
      </Row>
    </section>
  );
}

export default WhyChooseUs;

