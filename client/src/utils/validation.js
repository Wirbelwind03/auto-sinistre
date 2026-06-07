export function lettersOnly(event) {
    const regex = /^[a-zA-ZÀ-ÿ\s-]$/
    if (!regex.test(event.key)) {
        event.preventDefault()
    }
}

export function validateEmail(email){
      const emailRegex = /^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$/
      return emailRegex.test(email)
}