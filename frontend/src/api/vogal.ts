import axios from "axios";

const backend_address: string = import.meta.env.VITE_BACKEND_ADDRESS!

/* REALIZA REQUISIÇÃO PARA O BACKEND */
export const vogalRequest = async (frase: String) => {
    let res = await axios.post(`${backend_address}/vogal/encontrar`,
        {
            string: frase
        }
    )
    return res.data
}