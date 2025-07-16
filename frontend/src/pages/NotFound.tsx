import { Center, VStack, Heading, Button } from "@chakra-ui/react"
import { MdErrorOutline } from "react-icons/md"
import { useNavigate } from 'react-router-dom'

const NotFound = () => {
    const navigate = useNavigate()
    return (
        <Center h="100vh" bg="green.300">
            <VStack boxShadow="md" bg="whiteAlpha.900" p="10" rounded="md">
                <MdErrorOutline size={48} />
                <Heading as="h1" mt="5">404</Heading>
                <Heading as="h1" mb="5">Page Not Found...</Heading>
                <Button bg="green.400" onClick={() => navigate("/")}>Return to Home</Button>
            </VStack >
        </Center >
    )
}

export default NotFound