import Footer from "@/components/Footer"
import Header from "@/components/Header"
import Sidebar from "@/components/Sidebar"
import { useAuth } from "@/context/AuthContext"
import { Outlet } from "react-router"

const Dashboard = () => {
    const { session } = useAuth()
    console.log(session)

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
