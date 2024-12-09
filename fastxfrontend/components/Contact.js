import React from 'react';
import { Typography, Row, Col } from 'antd';
import Footer from './Footer';
import Header from './Header';

const { Title, Paragraph } = Typography;

function Contact() {
  return (
    <>
      <Header />
      <div style={{ height: "75vh", padding: '24px', maxWidth: '1200px', margin: 'auto' }}>
        <div style={{ marginBottom: '40px', textAlign: 'center' }}>
          <Title level={2} style={{ color: '#0963c5' }}>Contact Us</Title>
          <Paragraph style={{ fontSize: '16px', lineHeight: '1.6' }}>
            Get in touch with us for any queries, support, or information.
          </Paragraph>
        </div>
        <Row gutter={[16, 32]}>
          <Col xs={24} sm={12} md={8}>
            <div>
              <Title level={4} style={{ color: '#0963c5' }}>Headquarter 1</Title>
              <Paragraph style={{ fontSize: '14px', lineHeight: '1.6' }}>
                16-104, Pandurangapuram, Arilova Colony,<br />
                Visakhapatnam, Andhra Pradesh - 530040
              </Paragraph>
              <Paragraph style={{ fontSize: '14px', lineHeight: '1.6' }}>
                Phone: +91 9876543210<br />
                Email: contact.vizag@fastx.com
              </Paragraph>
            </div>
          </Col>
          <Col xs={24} sm={12} md={8}>
            <div>
              <Title level={4} style={{ color: '#0963c5' }}>Headquarter 2</Title>
              <Paragraph style={{ fontSize: '14px', lineHeight: '1.6' }}>
                DK-2/455, Danish Kunj, Kolor Road,<br />
                Bhopal, Madhya Pradesh - 462042
              </Paragraph>
              <Paragraph style={{ fontSize: '14px', lineHeight: '1.6' }}>
                Phone: +91 8765432109<br />
                Email: contact.bhopal@fastx.com
              </Paragraph>
            </div>
          </Col>
          <Col xs={24} sm={12} md={8}>
            <div>
              <Title level={4} style={{ color: '#0963c5' }}>Headquarter 3</Title>
              <Paragraph style={{ fontSize: '14px', lineHeight: '1.6' }}>
                Gandhi Nagar, Kakinada,<br />
                Andhra Pradesh - 533004
              </Paragraph>
              <Paragraph style={{ fontSize: '14px', lineHeight: '1.6' }}>
                Phone: +91 7654321098<br />
                Email: contact.kakinada@fastx.com
              </Paragraph>
            </div>
          </Col>
        </Row>
      </div>
      <Footer />
    </>

  );
}

export default Contact;