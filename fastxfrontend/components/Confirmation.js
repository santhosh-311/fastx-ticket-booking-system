import React, { useEffect, useState } from "react";
import { Result } from "antd";
import { CheckCircleOutlined } from "@ant-design/icons";
import { useNavigate } from "react-router-dom";

const Confirmation= () => {
    const navigate = useNavigate();
    const [countdown, setCountdown] = useState(5);

    useEffect(() => {
        const interval = setInterval(() => {
            setCountdown((prev) => prev - 1);
        }, 1000);

        const timer = setTimeout(() => {
            navigate("/booking");
        }, 5000);
        return () => {
            clearInterval(interval);
            clearTimeout(timer);
        };
    }, [navigate]);

    return (
        <Result
            icon={<CheckCircleOutlined style={{ color: "green", fontSize: "48px" }} />}
            title="Hurray! You have successfully booked your ticket."
            subTitle={`You will be redirected in ${countdown} seconds.`}
        />
    );
};

export default Confirmation;