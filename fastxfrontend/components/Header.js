import React, { useState } from 'react';
import axios from 'axios';
import { message } from "antd";

import {LogoutOutlined} from '@ant-design/icons'
import { Layout, Menu } from 'antd';
import { useNavigate } from 'react-router-dom';
import SignUpModal from './SignUpModal.js';
import LoginModal from './LoginModal';
import '../styles/Header.css';
import { useContext } from 'react';
import DataContext from './context/DataContext.js';

const { Header: AntHeader } = Layout;

function Header() {
  
  const navigate = useNavigate();

  const {userDetails,setUserDetails,setToken,loginFlag,setLoginFlag,setSignupFlag}=useContext(DataContext);
  const {userFlag,setUserFlag,opFlag,setOpFlag,adminFlag,setAdminFlag}=useContext(DataContext);
  let role;
  const handleMenuClick = (key) => {
    navigate(key);
  };

  const BASE_URL="http://localhost:8084/";

  const login=(email,password)=>{
    console.log("Logging In...")
    axios.post(BASE_URL+"user/login",{username:email,password})
    .then((res)=>{
      message.success("Login Successful")
      setToken(res.data.jwt);
      setUserDetails(res.data);
      setLoginFlag(true);
      role=res.data.role
      if(role==="USER")
        setUserFlag(true)
      else if(role==="OPERATOR")
        setOpFlag(true)
      else
        setAdminFlag(true)
      localStorage.setItem("token",res.data.jwt)
      localStorage.setItem("user",JSON.stringify(res.data))
      console.log(res.data);
    })
    .catch((err)=>{
      message.error("Login Failed")
      console.log("Error During Login "+err)
  })
  }

  const logout=()=>{
    message.success("Logged Out")
    localStorage.removeItem("user");
    localStorage.removeItem("token");

    setUserFlag(false);
    setOpFlag(false);
    setAdminFlag(false);

    setUserDetails("");
    setToken("");
    console.log(userDetails);
    navigate("/")
  }

  const signup=(userdata)=>{
    console.log("signup")
    axios.post(BASE_URL+"user/register",userdata)
    .then((res)=>{
      console.log("User registered Successful",res.data);
      setSignupFlag(true)
      message.success("Signup succesful")
    })
    .catch((err)=>{
      console.log("Error During Login "+err)
    message.error("Signup failed");    
  });
}
  return (
    <AntHeader className="header">
      <div className="logo" onClick={() => navigate('/')}>FastX</div>
      <Menu 
        theme="light" 
        mode="horizontal" 
        className="menu"
        onClick={({ key }) => handleMenuClick(key)}
        style={{minWidth:"800px", display:"flex", justifyContent:"center"}}
      >
        <Menu.Item key="/">Home</Menu.Item>
        <Menu.Item key="/about">About</Menu.Item>
        <Menu.Item key="/contact">Contact</Menu.Item>
        {
          userFlag?
          <Menu.Item key="/booking">Bookings</Menu.Item>
          :opFlag?
          <><Menu.Item key="/busdetails">Bookings</Menu.Item><Menu.Item key="/managebuses">Buses</Menu.Item></>
          :adminFlag?<><Menu.Item key="/routes">Routes</Menu.Item><Menu.Item key="/manageusers">Users</Menu.Item><Menu.Item key="/managebookinga">Bookings</Menu.Item></>:null
        }
        {
          userFlag || opFlag?<Menu.Item key='/profile'>Profile</Menu.Item>:null
        }

      </Menu>
      {
        userDetails===""?
        <div className="header-btns">
          <SignUpModal signup={signup}/>
          <LoginModal login={login} flag={loginFlag}/>
        </div>:
        <div className='user-details'>
          <p>{userDetails.userName.split('@')[0]}</p>
          <LogoutOutlined onClick={logout} className="logout-btn"/>
        </div>
      }

    </AntHeader>
  );
}

export default Header;

