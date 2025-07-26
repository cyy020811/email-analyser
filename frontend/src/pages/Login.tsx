import { Button, Center, Heading, Image, Stack } from "@chakra-ui/react"
import { FcGoogle } from "react-icons/fc";
import logo from '../assets/logo.png';
import { supabase } from "@/services/supabaseClient";

const Login = () => {

    const signIn = async () => {
        await supabase.auth.signInWithOAuth({
            provider: 'google',
            options: {
                redirectTo: `http://localhost:5173/auth/callback`,
                scopes: 'https://www.googleapis.com/auth/gmail.readonly',
                queryParams: {
                    access_type: 'offline',
                    prompt: 'consent',
                },
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