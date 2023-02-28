const LoginPage = () => {
  const redirectURI:string = 'http://localhost:3000/oauth2/redirect'
  const AUTH_URL:string = `http://localhost:8080/oauth2/authorization/kakao?redirect_uri=${redirectURI}`;
  const socialLogin = () => {
    window.location.href = AUTH_URL
  }

  return (
    <div>
      <button onClick={socialLogin}>카카오로그인</button>
    </div>
  )
}

export default LoginPage;