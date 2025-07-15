import { Navigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import type { ReactNode } from 'react';
import { SkeletonText } from '@chakra-ui/react';

export default function ProtectedRoute({ children }: { children: ReactNode }) {
    const { user, loading } = useAuth();

    if (loading) return <SkeletonText noOfLines={3} gap="4" />;
    if (!user) return <Navigate to="/login" />;

    return children;
}