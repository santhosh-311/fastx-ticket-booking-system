import React, { useContext, useState,useEffect } from "react";
import { Typography,message, Modal, Button, Input, DatePicker, Radio, Form} from "antd";
import DataContext from "./context/DataContext";
import axios from "axios";


// const { Text } = Typography;

const SignUpModal = ({signup}) => {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [form] = Form.useForm();

  const {signupFlag,setSignupFlag}=useContext(DataContext)

  const [opFlag,setOpFlag]=useState(false);

  const [confirm,setConfirm]=useState(false);
  const [generatedOTP,setGeneratedOTP] = useState();
  const [isVerified,setIsVerified] = useState(false);
  const [count,setCount]=useState(2);
  const BASE_URL="http://localhost:8084";

  const { Title} = Typography;

  
  useEffect(()=>{
    if(signupFlag===false){
      setIsModalOpen(true);
    }
  },[signupFlag])

  const showModal = () => {
    setIsModalOpen(true);
  };

  const handleCancel = () => {
    setConfirm(false);
    setIsVerified(false);
    setSignupFlag(true)
    setIsModalOpen(false);
  };

  const handleSignup = (values) => {
    console.log("Form values:", values);

    let role=opFlag?"OPERATOR":"USER";
     const userdata = {
        ...values,
        dob: values.dob.format("YYYY-MM-DD"),
        roles:role
    };
    
  console.log(userdata);

  signup(userdata);
  setConfirm(false);
  setIsVerified(false);
  setOpFlag(false);
  setSignupFlag(true);
  setIsModalOpen(false);
};


const sendOtp= async (userName)=>{
  try{
    console.log(userName);
    const otp=parseInt(Math.floor(1000 + Math.random() * 9000));
    setGeneratedOTP(otp);
    await axios.post(`${BASE_URL}/user/sendotp/${"signup"}/${userName}/${otp}`);
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


return (
  <div>
    <Button onClick={showModal}>Sign Up</Button>
    <Modal
      title="Sign Up"
      open={isModalOpen}
      onCancel={handleCancel}
      footer={null}
      width={700}
    >
      <div style={{ textAlign: "right",marginBottom:"20px" }}>
          {/* <Text>Don't have an account yet? </Text> */}
          {
            !confirm?<button className="signup-link-btn" onClick={()=>{
              setOpFlag(!opFlag);
            }}>{opFlag===true?
              <span>Back</span>: <span>Operator SignUp</span>
            }</button>:null
          }
        </div>
      <Form
        form={form}
        layout="vertical"
        onFinish={(values) => {
          console.log("In onfinish")
          if (!confirm && !isVerified) {
            console.log(opFlag)
            sendOtp(values.email);
          } else if(confirm && !isVerified) {
            verifyOtp(values.inputOtp);
          }
          else{
            handleSignup(values);
          }
        }}
        initialValues={{ gender: "Male" }}
      >
        { !confirm || (confirm && isVerified)?
        <div className="form-container">

            <div className="form-left">
                <Form.Item
                  label="Name"
                  name="name"
                  rules={[{ required: true, message: "Please enter your name" }]}
                >
                  <Input disabled={isVerified} placeholder="Enter your name" />
                </Form.Item>

                <Form.Item
                  label="Email"
                  name="email"
                  rules={[
                    { required: true, message: "Please enter your email" },
                    { type: "email", message: "Please enter a valid email address" },
                  ]}
                >
                  <Input disabled={isVerified} placeholder="Enter your email" />
                </Form.Item>

                <Form.Item
                  label="Password"
                  name="password"
                  rules={[{ required: true, message: "Please enter your password" }]}
                >
                  <Input.Password disabled={isVerified} placeholder="Enter your password" />
                </Form.Item>

                <Form.Item
                  label="Phone Number"
                  name="number"
                  rules={[
                    { required: true, message: "Please enter your phone number" },
                    {
                      pattern: /^[0-9]{10}$/,
                      message: "Enter a valid 10-digit number",
                    },
                  ]}
                >
                  <Input disabled={isVerified} placeholder="Enter your phone number" />
                </Form.Item>

                <Form.Item
                  label="Date of Birth"
                  name="dob"
                  rules={[
                    { required: true, message: "Please select your date of birth" },
                  ]}
                >
                  <DatePicker disabled={isVerified} style={{ width: "100%" }} />
                </Form.Item>

                <Form.Item
                  label="Gender"
                  name="gender"
                  rules={[{ required: true, message: "Please select your gender" }]}
                >
                  <Radio.Group disabled={isVerified}>
                    <Radio value="Male">Male</Radio>
                    <Radio value="Female">Female</Radio>
                    <Radio value="Other">Other</Radio>
                  </Radio.Group>
                </Form.Item>
            </div>

            <div className="form-right">
                <Form.Item
                  label="Address"
                  name="address"
                  rules={[
                    { required: true, message: "Please enter your address" },
                    {
                      pattern: /^[A-Za-z0-9 ,/-]{5,30}$/,
                      message: "Address should only contain alphabets,spaces,Numbers,/,-,','",
                    },
                  ]}
                >
                  <Input disabled={isVerified} placeholder="Enter your city" />
                </Form.Item>

                <Form.Item
                  label="City"
                  name="city"
                  rules={[
                    { required: true, message: "Please enter your city" },
                    {
                      pattern: /^[A-Za-z ]+$/,
                      message: "City should only contain alphabets and spaces",
                    },
                  ]}
                >
                  <Input disabled={isVerified} placeholder="Enter your city" />
                </Form.Item>

                <Form.Item
                  label="State"
                  name="state"
                  rules={[
                    { required: true, message: "Please enter your state" },
                    {
                      pattern: /^[A-Za-z ]+$/,
                      message: "State should only contain alphabets and spaces",
                    },
                  ]}
                >
                  <Input disabled={isVerified} placeholder="Enter your state" />
                </Form.Item>
                <Form.Item
                  label="Country"
                  name="country"
                  rules={[
                    { required: true, message: "Please enter your Country" },
                    {
                      pattern: /^[A-Za-z ]+$/,
                      message: "Country should only contain alphabets and spaces",
                    },
                  ]}
                >
                  <Input disabled={isVerified} placeholder="Enter your Country" />
                </Form.Item>
                {
                  opFlag===true?(
                    <>
                              <Form.Item
                            label="Pan Number"
                            name="pan"
                            rules={[
                              { required: true, message: "Please enter your Pan Number" },
                              {
                                pattern: /^[A-Z0-9]{10}$/,
                                message: "Pan Number should only contain Capitals and Numbers",
                              },
                            ]}
                          >
                            <Input disabled={isVerified} placeholder="Enter your Pan Number" />
                          </Form.Item>

                          <Form.Item
                            label="Aadhaar Number"
                            name="aadhar"
                            rules={[
                              { required: true, message: "Please enter your Aadhar Number" },
                              {
                                pattern: /^[0-9]{12}$/,
                                message: "Aadhaar Number should only contain Numbers",
                              },
                            ]}
                          >
                            <Input disabled={isVerified} placeholder="Enter your Aadhaar Number" />
                          </Form.Item>
                    
                    </>
                  )
                  
                :null
                }
            </div>
        </div>
      :confirm && !isVerified?
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
            </Form.Item>:
            <Title level={4} style={{ marginBottom: "10px",marginTop:"2px" }}>OTP Verified</Title>}
        <Form.Item>
        {confirm && !isVerified? (
              <Button type="primary" htmlType="submit" block>Verify</Button>
            ) : confirm && isVerified?(
              <Button type="primary" htmlType="submit">Sign Up</Button>
            ) :(
              <Button type="primary" htmlType="submit" block>Next</Button>
            )}
        </Form.Item>
      </Form>
    </Modal>
  </div>
);
};

export default SignUpModal;