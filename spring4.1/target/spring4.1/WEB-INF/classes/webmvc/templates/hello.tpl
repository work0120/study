yieldUnescaped '<!DOCTYPE html>'
html(lang:'en') {
  head {
    meta('http-equiv':'"Content-Type" content="text/html; charset=utf-8"')
    title('hello groovy templates')
  }
  body {
      div("hello $user.name")
  }
}