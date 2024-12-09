import React from 'react';
import { Typography, Row, Col, Avatar } from 'antd';
import dinesh from '../utility/dinesh_photo.jpg';
import meghna from '../utility/meghna_photo.jpg';
import sanjay from '../utility/sanjay_photo.jpg';
import Footer from './Footer';
import Header from './Header';

const { Title, Paragraph } = Typography;

function About() {
  return (
    <>
      <Header />
      <div style={{ padding: '24px', maxWidth: '1200px', margin: 'auto' }}>
        <div style={{ marginBottom: '40px' }}>
          <Title level={2} style={{ color: '#0963c5' }}>About Us</Title>
          <Paragraph style={{ fontSize: '16px', lineHeight: '1.6' }}>
            FastX is India's premier online bus ticket booking platform, designed to make travel
            seamless, convenient, and reliable. Founded in 2024, FastX connects millions of travelers
            with a wide range of bus operators, ensuring affordable pricing, excellent customer service,
            and unmatched benefits. Our mission is to redefine bus travel and expand into international
            markets to bring exceptional travel experiences to everyone.
          </Paragraph>
        </div>
        <div>
          <Title level={3} style={{ color: '#0963c5' }}>Management Team</Title>
          <Row gutter={[16, 32]} style={{ marginTop: '20px' }}>
            <Col xs={24} sm={12} md={8}>
              <div style={{ textAlign: 'center' }}>
                <Avatar
                  size={120}
                  src={dinesh}
                  style={{ marginBottom: '10px' }}
                />
                <Title level={4} style={{ color: 'black', margin: 0 }}>Dinesh Santhosh Kumar Kosuru, CEO</Title>
                <Paragraph style={{ fontSize: '14px', lineHeight: '1.6' }}>
                  Dinesh leads FastX as CEO, driving the company’s vision to revolutionize bus travel.
                  With over a decade of experience in transportation and logistics, he ensures seamless
                  operations and growth. Dinesh holds an  from IIM Bangalore and has a proven track
                  record of innovation and leadership.
                </Paragraph>
              </div>
            </Col>
            <Col xs={24} sm={12} md={8}>
              <div style={{ textAlign: 'center' }}>
                <Avatar
                  size={120}
                  src={meghna}
                  style={{ marginBottom: '15px' }}
                />
                <Title level={4} style={{ color: 'black', margin: 0 }}>Meghna Gupta, CTO</Title>
                <Paragraph style={{ fontSize: '14px', lineHeight: '1.6' }}>
                  Meghna oversees FastX's technological advancements as CTO. With expertise in building
                  scalable and innovative tech platforms, she ensures seamless user experiences. Meghna
                  holds a Computer Science degree from IIT Delhi and has 12+ years of experience in the
                  software development and technology sectors.
                </Paragraph>
              </div>
            </Col>
            <Col xs={24} sm={12} md={8}>
              <div style={{ textAlign: 'center' }}>
                <Avatar
                  size={120}
                  src={sanjay}
                  style={{ marginBottom: '15px' }}
                />
                <Title level={4} style={{ color: 'black', margin: 0 }}>Sanjay Bhargav Vaddiparthi, COO</Title>
                <Paragraph style={{ fontSize: '14px', lineHeight: '1.6' }}>
                  Sanjay plays a pivotal role as COO, ensuring smooth operations and service delivery
                  at FastX. With a strong background in operations management, Sanjay has over 10 years
                  of experience in the transportation and travel industry. He holds a Master’s degree
                  in Business Administration from ISB Hyderabad.
                </Paragraph>
              </div>
            </Col>
          </Row>
        </div>
      </div>
      <Footer />

    </>
  );
}

export default About;