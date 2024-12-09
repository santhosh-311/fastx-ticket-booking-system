
import { Route, Routes } from "react-router-dom";
import Home from "./Home";
import About from "./About"
import Contact from "./Contact";
import LoginModal from "./LoginModal";
import Booking from "./Booking";
import BusBooking from "./BusBooking";
import UpdateProfile from "./UpdateProfile";
import RouteManagement from "./RouteManagement";
import ManageUsers from "./ManageUsers";
import ManageBookingA from "./ManageBookingA";
import BusDetails from "./BusDetails";
import ManageBookings from "./ManageBookings";
import ManageBuses from "./ManageBuses";
import Payment from "./Payment";
import Confirmation from "./Confirmation";


const Routing=()=>{
    return(<>
        <Routes>
            <Route path="/" element={<Home/>}/>
            <Route path="/about" element={<About/>}/>
            <Route path="/contact" element={<Contact/>}/>
            <Route path="/login" element={<LoginModal/>}/>
            <Route path="/booking" element={<Booking/>}/>
            <Route path="/busbooking" element={<BusBooking/>}/>
            <Route path="/profile" element={<UpdateProfile/>}/>
            <Route path="/routes" element={<RouteManagement/>}/>
            <Route path="/manageusers" element={<ManageUsers/>}/>
            <Route path="/managebookinga" element={<ManageBookingA/>}/>
            <Route path="/busdetails" element={<BusDetails/>}/>
            <Route path="/managebookings" element={<ManageBookings/>}/>
            <Route path="/managebuses" element={<ManageBuses/>}/>
            <Route path="/payment" element={<Payment/>}/>
            <Route path="/confirmation" element={<Confirmation/>}/>
        </Routes>
    
    </>)
}
export default Routing;