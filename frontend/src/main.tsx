import { Provider as UIProvider } from "@/components/ui/provider"
import { AuthProvider } from "./context/AuthContext.tsx";
import { BrowserRouter as Router } from "react-router-dom";
import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.tsx'

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <UIProvider>
      <AuthProvider>
        <Router>
          <App />
        </Router>
      </AuthProvider>
    </UIProvider>
  </StrictMode>,
)
