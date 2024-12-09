import React from 'react';
import { Typography } from 'antd';
import '../styles/Hero.css';

const { Title, Paragraph } = Typography;

function Hero() {
  return (
    <section className="hero">
      <div className="container">
        <Title level={1} className="title-hero">
          Welcome to FastX - Your Ultimate Online Bus Ticket Booking Solution
        </Title>
        <Paragraph className="description">
          Traveling has never been easier! With our user-friendly online bus ticket booking system, 
          you can quickly and conveniently plan your next journey, book seats, and manage 
          reservations—all in one place.
        </Paragraph>
      </div>
    </section>
  );
}

export default Hero;

