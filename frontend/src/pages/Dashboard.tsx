import Footer from "@/components/Footer"
import Header from "@/components/Header"
import Sidebar from "@/components/Sidebar"
import { Outlet } from "react-router"

const Dashboard = () => {
    return (
        <>
            <Sidebar />
            <Header />
            <Outlet />
            <Footer />
        </>
    )
}

export default Dashboard
