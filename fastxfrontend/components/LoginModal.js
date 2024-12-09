import React, { useContext, useEffect, useState } from "react";
import { Modal, Button, Input, Divider, Typography } from "antd";
import { GoogleOutlined, EyeInvisibleOutlined, EyeTwoTone } from "@ant-design/icons";
import DataContext from "./context/DataContext";
import ForgetPassword from "./ForgetPassword";

const { Text } = Typography;

const LoginModal = ({login}) => {
  const{loginFlag,setSignupFlag}=useContext(DataContext)
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const showModal = () => {
    setIsModalOpen(true);
  };

  useEffect(()=>{
    if(loginFlag===false){
      setIsModalOpen(true);
    }
  },[loginFlag])


  const handleOk = () => {
    setIsModalOpen(false);
  };

  const handleCancel = () => {
    setIsModalOpen(false);
  };

  return (
    <div>
      <Button type="primary" onClick={showModal}>
        Login
      </Button>
      <Modal
        title={<h2>Sign In</h2>}
        open={isModalOpen}
        onOk={handleOk}
        onCancel={handleCancel}
        footer={null}
        width={400}
      >
        <div style={{ textAlign: "center" }}>
          <Text>Don't have an account yet? </Text>
          <button className="signup-link-btn" onClick={()=>{
            setSignupFlag(false);
            handleCancel();
          }}>Sign up here</button>
        </div>
        <Input
          placeholder="Enter email address"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          style={{ marginTop: 20 }}
        />
        <Input.Password
          placeholder="Enter password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          iconRender={(visible) =>
            visible ? <EyeTwoTone /> : <EyeInvisibleOutlined />
          }
          style={{ marginTop: 15 }}
        />
        <div style={{ textAlign: "right", marginTop: 10 }}>
          <ForgetPassword onForgetPassword={handleCancel} userName={email}/>
        </div>
        <Button
          type="primary"
          block
          onClick={()=>{
            login(email,password);
            handleOk();
          }}
          style={{ marginTop: 20 }}
        >
          Sign In
        </Button>
        <Divider>OR</Divider>
        <Button
          icon={<GoogleOutlined />}
          type="default"
          block
          style={{ marginTop: 10 }}
        >
          Sign in with Google
        </Button>
        <div style={{ fontSize: 12, marginTop: 15, textAlign: "center" }}>
          By signing in or creating an account, you are agreeing to our{" "}
          <a href="/terms-and-conditions">Terms & Conditions</a> and our{" "}
          <a href="/privacy-policy">Privacy Policy</a>.
        </div>
      </Modal>
    </div>
  );
};

export default LoginModal;