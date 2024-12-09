import Header from "./Header";
import Hero from "./Hero";
import SearchForm from "./SearchForm";
import AvailableBusses from "./AvailableBusses";
import WhyChooseUs from "./WhyChooseUs";
import { useNavigate } from "react-router-dom";
import Footer from "./Footer";

const Home=()=>{
    let nav=useNavigate();

    

    return(<>
    <Header/>
    <Hero/>
    <SearchForm/>
    <AvailableBusses/>
    <WhyChooseUs />
    <Footer/>
    </>)
}
export default Home;