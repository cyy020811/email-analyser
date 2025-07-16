import { Button, Center, Heading, Image, Stack } from "@chakra-ui/react"
import { FcGoogle } from "react-icons/fc";
import logo from '../assets/logo.png';
import { supabase } from "@/services/supabaseClient";

const Login = () => {

    const signIn = async () => {
        await supabase.auth.signInWithOAuth({
            provider: 'google',
            options: {
                redirectTo: `${window.location.origin}/dashboard`
            }
        })
    }

    return (
        <Center h="100vh" bg="green.300">
            <Stack boxShadow="md" bg="whiteAlpha.900" p="10" rounded="md">
                <Image src={logo} maxW="40px" mx="auto" />
                <Heading as="h1">Welcome to Email Analyser</Heading>
                <Button variant="outline" onClick={signIn}>
                    <FcGoogle />
                    Continue with Google
                </Button>
            </Stack>
        </Center>
    )
}

export default Login