import { useState } from "react";
import { Modal, Input, Button, Form, message } from "antd";
import axios from "axios";


const ForgetPassword = ({ onForgetPassword, userName }) => {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [confirm, setConfirm] = useState(false);
  const [generatedOTP,setGeneratedOTP] = useState();
  const [isVerified,setIsVerified] = useState(false);
  const [count,setCount]=useState(2);
  // const [userOtp,setuserOtp]=useState();

  const BASE_URL="http://localhost:8084";

  const [form] = Form.useForm();

  const showModal = () => {
    onForgetPassword();
    setIsModalOpen(true);
  };

  const handleCancel = () => {
    setConfirm(false);
    setIsModalOpen(false);
    form.resetFields(); // Reset form fields when the modal is closed
  };

  const handleOk = () => {
    console.log("in handle ok");
  };

  const sendOtp= async (userName)=>{
    try{
      console.log(userName);
      const otp=parseInt(Math.floor(1000 + Math.random() * 9000));
      setGeneratedOTP(otp);
      await axios.post(`${BASE_URL}/user/sendotp/${"recovery"}/${userName}/${otp}`);
      message.success("OTP sent successfully");
      setConfirm(true);
    }
    catch(error){
      message.error("OTP sending failed");
      console.log("Error: ",error);
    }
  }

  const verifyOtp=(inputOtp)=>{
    if(generatedOTP===parseInt(inputOtp)){
      setIsVerified(true);
      message.success("OTP Verified");
    }
    else{
      setCount(count-1);
      if(count===0){
        setConfirm(false);
        setCount(2);
        handleCancel();
      }
      message.error("OTP invalid!");
      message.warning(`Remaining Tries: ${count}`);
    }
  }

  const updatePassword= async(userName,password)=>{
    try{
      console.log("In update password");
      axios.put(`${BASE_URL}/user/forgetpassword/${userName}/${password}`)
      message.success("Password Updated");
      handleCancel();
    }
    catch(error){
      message.error("Updating Password failed!")
    }
  }


  return (
    <>
      <Button style={{border:"0", outline:"none", backgroundColor:"transparent", fontWeight:"500"}} onClick={showModal}>Forget Password?</Button>
      <Modal
        title={<h2 style={{ textAlign: "center" }}>Forget Password</h2>}
        open={isModalOpen}
        onOk={handleOk}
        onCancel={handleCancel}
        footer={null}
        width={400}
      >
        <Form
          form={form}
          layout="vertical"
          onFinish={(values) => {
            if (!confirm && !isVerified) {
              sendOtp(values.userName);
            } else if(confirm && !isVerified) {
              verifyOtp(values.inputOtp);
            }
            else{
              updatePassword(values.userName,values.password)
            }
          }}
        >
          <Form.Item
            label="Email Address"
            name="userName"
            initialValue={userName}
            rules={[
              { required: true, message: "Email is required!" },
              { type: "email", message: "Enter a valid email!" },
            ]}
          >
            <Input placeholder="Enter email address" disabled={confirm} />
          </Form.Item>

          {confirm && !isVerified && (
            <Form.Item
              label="OTP"
              name="inputOtp"
              rules={[
                { required: true, message: "OTP is required!" },
                { pattern:/^[0-9]{4}$/, message:"OTP should be 4 digits" },
              ]}
            >
              <Input
                placeholder="Enter 4 digit code"
              />
            </Form.Item>
          )}
          {isVerified &&(
            <Form.Item
              label="New Password"
              name="password"
              rules={[
                {required:true, message:"Password is required!"},
              ]}
              >
                <Input placeholder="Enter new password"/>
              </Form.Item>
          )
          }

          <Form.Item>
            {confirm && !isVerified? (
              <Button type="primary" htmlType="submit" block>Verify</Button>
            ) : confirm && isVerified?(
              <Button type="primary" htmlType="submit">Update Password</Button>
            ) :(
              <Button type="primary" htmlType="submit" block>Confirm</Button>
            )}
          </Form.Item>
        </Form>
      </Modal>
    </>
  );
};

export default ForgetPassword;
