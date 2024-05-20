import axios from "axios";
import { Ref, ref } from "vue";

export const frase: Ref<String> = ref("teste")
export const arrayChars: Ref<String[]> = ref([])
export const alvo: Ref<String> = ref("")
export const mostrarResultado: Ref<boolean> = ref(false)

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

/* RECUPERA A VOGAL ALVO NA FRASE */
export const recuperaVogal = async (fraseAlvo: String) => {
    let res = await vogalRequest(fraseAlvo)
    alvo.value = res.vogal
    if (res.vogal) arrayChars.value = fraseAlvo.split("")
    else arrayChars.value = "A Entrada Fornecida Não Produziu um Resultado".split("")
    mostrarResultado.value = true
}