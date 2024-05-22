import axios from "axios";
import { Ref, ref } from "vue";
import { vogalRequest } from "../api/vogal";

export const frase: Ref<String> = ref("")
export const arrayChars: Ref<String[]> = ref([])
export const alvo: Ref<String> = ref("")
export const mostrarResultado: Ref<boolean> = ref(false)

/* RECUPERA A VOGAL ALVO NA FRASE */
export const recuperaVogal = async (fraseAlvo: String) => {
    let res = await vogalRequest(fraseAlvo)
    alvo.value = res.vogal
    if (res.vogal) arrayChars.value = fraseAlvo.split("")
    else arrayChars.value = "A Entrada Fornecida Não Produziu um Resultado".split("")
    mostrarResultado.value = true
}