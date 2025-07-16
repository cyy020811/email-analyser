import { supabase } from "@/services/supabaseClient"
import { Button } from "@chakra-ui/react"

const Sidebar = () => {
    const signOut = async () => {
        const { error } = await supabase.auth.signOut()
    }

    return (
        <div>
            Sidebar
            <Button onClick={signOut}></Button>
        </div>
    )
}

export default Sidebar