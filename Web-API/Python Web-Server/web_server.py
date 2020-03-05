# Run web api
import http.server
import socketserver
import threading
import webbrowser
def web_api_run():
    try:
        PORT = 3452
        Handler = http.server.SimpleHTTPRequestHandler
        with socketserver.TCPServer(("localhost", PORT), Handler) as httpd:
            print("serving at port", PORT)
            httpd.serve_forever()
    except Exception as error :
        print(error)

pass
def web_api_thread():
    to=threading.Thread(target=web_api_run)
    to.start()
pass

web_api_thread()
webbrowser.open("http://localhost:3452/")
