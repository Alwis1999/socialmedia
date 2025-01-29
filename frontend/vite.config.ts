import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
<<<<<<< HEAD
  define: {
    global: 'window',  // Maps `global` to `window` for compatibility
  },
=======
>>>>>>> 4e77c164fe132508d1b54630b330c53dac3a55bc
})
