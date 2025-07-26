import { useEffect, useState } from 'react';
import { supabase } from '@/services/supabaseClient';
import type { Session } from '@supabase/supabase-js';
import { Navigate } from 'react-router-dom';
const AuthCallback = () => {
    const [session, setSession] = useState<Session | null>(null);

    useEffect(() => {
        const { data: listener } = supabase.auth.onAuthStateChange((_event, session) => {
            setSession(session);
        });

        return () => {
            listener.subscription.unsubscribe();
        };
    }, []);

    return session ? <Navigate to="/dashboard" /> : <p>Authenticating...</p>;
};


export default AuthCallback