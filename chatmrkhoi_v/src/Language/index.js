import { createI18n } from 'vue-i18n'
import vn from "./vn.js"
import en from "./en.js"

export default () => {

    const messages = {
        vn: vn,
        en: en,
      }
      const i18n = createI18n({
        locale: localStorage.getItem("language") == null ? 'vn' : localStorage.getItem("language"), // set locale
        fallbackLocale: 'en', // set fallback locale
        messages,
      })

    return {
        i18n
    }
}

