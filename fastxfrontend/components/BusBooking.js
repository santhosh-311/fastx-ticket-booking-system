import React, { useContext, useEffect, useState } from 'react';
import { Button, message } from 'antd';
import { useLocation, useNavigate } from 'react-router-dom';
import axios from 'axios';
import '../styles/BusBooking.css';
import DataContext from './context/DataContext';
import Header from './Header';
import Footer from './Footer';

const BusBooking = () => {
    const location = useLocation();
    const [busDetails, setBusDetails] = useState(location.state?.bus || null);
    const [bookedSeats, setBookedSeats] = useState([]);
    const [blockedSeats,setBlockedSeats]=useState([]);
    const [selectedSeats, setSelectedSeats] = useState([]);
    const nav = useNavigate();
    const busId = busDetails?.busId;
    const {token,userDetails,setBookingDetails} = useContext(DataContext)


    useEffect(() => {
        if (token && busDetails && busId) {
            axios.get(`http://localhost:8084/seat/getseats/${busId}`, {
                headers: { Authorization: `Bearer ${token}` },
            })
                .then(response => {
                    console.log("Seats from backend",response.data)
                    setBookedSeats(response.data.filter(seat => !seat.available && seat.booking!==null).map(seat => {
                        console.log("Booked seat: ",seat)
                        return seat.seatNumber
                    }));

                    setBlockedSeats(response.data.filter(seat => !seat.available && seat.booking===null).map(seat => {
                        console.log("Blocked seat: ",seat)
                        return seat.seatNumber
                    }))

                    console.log("Booked Seats List ",bookedSeats)
                    console.log("Blocked Seats List ",blockedSeats)
                }).catch(error => {
                    console.error("Error fetching seat details:", error);
                    message.error("Could not fetch seat details.");
                });
        }
    }, [token, busDetails, busId]);

    const handleSeatSelect = (seat) => {
        console.log(seat);
        if (selectedSeats.length >= 4 && !selectedSeats.includes(seat)) {
            message.warning('You can only select up to 4 seats');
            return;
        }
        setSelectedSeats(prev => (
            prev.includes(seat)
                ? prev.filter(s => s !== seat)
                : [...prev, seat]
        ));
    };

    const handleBooking = () => {
        if (selectedSeats.length === 0) {
            message.warning('Please select at least one seat');
            return;
        }
        const userName = userDetails.userName;
        console.log(userName)
        console.log(token);
        console.log(selectedSeats.join(","))
        axios.post(`http://localhost:8084/booking/user/create/${userName}/${busId}`, {seatInfo:selectedSeats.join(",")}, {
            headers: { Authorization: `Bearer ${token}` }
        })
            .then((response) => {
                console.log(response.data)
                setBookingDetails(response.data);
                message.success("Seats booked successfully!");
                nav('/payment', { state: { selectedSeats,userName, busId } });
            })
            .catch(error => {
                message.error("Error while booking seats.");
                console.error("Booking error:", error);
            });
    };

    const handleBlocking= async ()=>{
        try{
            if(selectedSeats.length===0){
                message.warning("Please select atleast 1 seat");
                return;
            }
            console.log("selectSeats:",selectedSeats);
            const seatNumbers=selectedSeats.join(",")
            const response = await axios.put(`http://localhost:8084/seat/op/block/${busId}/${seatNumbers}`,{},
            {
                headers: { Authorization: `Bearer ${userDetails.jwt}` }
            });
            if(response.data.includes("Seats Blocked"))
                message.success("Seats Blocked Sucessfully");
            else
                message.error("Seats Blocking Failed");
            
        }
        catch(error){
            message.error("API issue: Seats Blocking Failed ");
            console.log("ERROR: ",error);
        }
        
    }

    const handleUnblocking= async()=>{
        try{
            if(selectedSeats.length===0){
                message.warning("Please select atleast 1 seat")
                return;
            }
            console.log("selected seats: ",selectedSeats);
            const seatNumbers=selectedSeats.join(",")
            const response = await axios.put(`http://localhost:8084/seat/op/unblock/${busId}/${seatNumbers}`,{},
            {
                headers: { Authorization: `Bearer ${userDetails.jwt}` }
            });
            if(response.data.includes("Seats Unblocked"))
                message.success("Seats Unblocked Sucessfully");
            else
                message.error("Seats Unblocking Failed");
        }
        catch(error){
            message.error("API issue: Seats Unblocking Failed");
            console.log("ERROR: ",error);
        }
    }


    const handleCancel = () => {
        nav('/');
    };

    const renderSeats = () => {
        if (!busDetails) return null;
        const seatLayout = [
            ['', '', '', '', "Driver's Seat"],
            ['1', '2', '', '3', '4'],
            ['5', '6', '', '7', '8'],
            ['9', '10', '', '11', '12'],
            ['13', '14', '', '15', '16'],
            ['17', '18', '', '19', '20'],
            ['21', '22', '', '23', '24'],
            ['25', '26', '', '27', '28'],
            ['29', '30', '', '31', '32'],
            ['33', '34', '', '35', '36']
        ];
        return (
            <div className='seat-container'>
                <div className='seat-info'>
                    <p className='box-info'><span className='box white'></span>Available</p>
                    <p className='box-info'><span className='box red'></span>Booked</p>
                    <p className='box-info'><span className='box blue'></span>Selected</p>
                    <p className='box-info'><span className='box gray'></span>Blocked</p>
                </div>
                <div className="bus-layout">
                    {seatLayout.map((row, rowIndex) => (
                        <div key={rowIndex} className="seat-row">
                            {/* <p>{row}|| {rowIndex}</p> */}
                            {row.map((seat, seatIndex) => {
                                if (!seat) return <div key={seatIndex} className="seat-spacer"></div>;
                                if (seat === "Driver's Seat") {
                                    return (
                                        <div key="driver" className="drivers-seat">
                                            {seat}
                                        </div>
                                    );
                                }
                                const isBooked = bookedSeats.includes(seat);
                                const isSelected = selectedSeats.includes(seat);
                                const isBlocked = blockedSeats.includes(seat);
                                return (
                                    <button
                                        key={seat}
                                        className={`seat ${isBooked ? 'booked' : ''} ${isSelected ? 'selected' : ''} ${isBlocked ? 'blocked' : ''} ${ isBlocked && userDetails.role === "USER" ? 'cursor-block' : ''}`}
                                    onClick = {() => {
                                        if(!isBooked){
                                            if((isBlocked && userDetails.role==="OPERATOR") || !isBlocked)
                            handleSeatSelect(seat);
                                        }
                                    }}
                            disabled={isBooked || userDetails.role === "OPERATOR" ? false : isBlocked}
                                >
                            {seat}
                        </button>
                    );
                        })}
                </div>
                ))}
            </div>
            </div >
        );
    };

    if (!busDetails) return <div className="loading">Loading bus details...</div>;

    const { route, busName, busType, busNumber, pricePerSeat } = busDetails;
    const tax = selectedSeats.length * pricePerSeat * 0.18;
    const total = selectedSeats.length * pricePerSeat * 1.18;

    return (
        <>
        <Header/>
        <div className="bus-booking-container">
            <div className="journey-details">
                <div className="journey-details-left">
                    <h2>{route?.routeFrom} ⇌ {route?.routeTo}</h2>
                    <div className="timing">
                        <p>Pickup: {route?.boardingPoint} at {busDetails?.departure}</p>
                        <p>Drop-off: {route?.dropingPoint} at {busDetails?.arrival}</p>
                    </div>
                </div>
                <div className="journey-details-right">
                    <h3>{busName}</h3>
                    <p>Bus Type: {busType}</p>
                    <p>Bus Number: {busNumber}</p>
                </div>
            </div>
            <div className="main-content">
                <div className="seat-layout">{renderSeats()}</div>
                <div className="ticket-summary">
                    <h3>Ticket Summary</h3>
                    <p>Selected Seats: {selectedSeats.join(', ') || 'None'}</p>
                    <p>Fare: ₹{(selectedSeats.length * pricePerSeat).toFixed(2)}</p>
                    <p>Tax: ₹{tax.toFixed(2)}</p>
                    <p className="total">Total: ₹{total.toFixed(2)}</p>
                    {
                        userDetails.role==="OPERATOR"?<div style={{display:"flex",gap:"10px"}}><Button style={{backgroundColor:"#f44336", color:"white"}} onClick={handleBlocking}>Block Seats</Button><Button onClick={handleUnblocking} type="primary">Unblock Seats</Button></div>:
                        <Button type="primary" block onClick={handleBooking}>Book Seats</Button>

                    }
                    <Button onClick={handleCancel}>Cancel</Button>
                </div>
            </div>
        </div>
        <Footer/>
        </>
    );
};

export default BusBooking;
