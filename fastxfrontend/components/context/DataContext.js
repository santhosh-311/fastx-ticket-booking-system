import { createContext, useState } from "react";

const DataContext = createContext({});

export const DataProvider = ({children})=>{
    const [buses,setBuses]=useState([]);
    const availablebuses=(busList)=>{
        console.log("Available Buses:",busList)
        setBuses(busList);
    }
    const [userDetails,setUserDetails]=useState("");
    const [token,setToken]=useState("");

    const [loginFlag,setLoginFlag]=useState(false);
    const [signupFlag,setSignupFlag]=useState(true);
    const [forgetFlag,setForgetFlag]=useState(false);

    const [routesFrom,setRoutesFrom] =useState([])
    const [routesTo,setRoutesTo] =useState([])

    const [userFlag,setUserFlag]=useState(false);
    const [opFlag,setOpFlag]=useState(false);
    const [adminFlag,setAdminFlag]=useState(false);
    const [bookingDetails,setBookingDetails]=useState([]);

    return(
        <DataContext.Provider value={{
            buses,availablebuses,
            userDetails,setUserDetails,token,setToken,
            loginFlag,setLoginFlag,
            signupFlag,setSignupFlag,
            userFlag,setUserFlag,
            opFlag,setOpFlag,
            adminFlag,setAdminFlag,
            routesFrom,setRoutesFrom,
            routesTo,setRoutesTo,
            forgetFlag,setForgetFlag,
            bookingDetails,setBookingDetails
        }}>
            {children}
            </DataContext.Provider>
    )
}

export default DataContext;