// Server.kt
import java.net.ServerSocket
import java.net.InetAddress
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStream
import java.io.PrintWriter

class Server {
    fun startServer() {
        val ipAddress = "130.184.252.79"
        val port = 8080

        try {
            val serverSocket = ServerSocket(port, 50, InetAddress.getByName(ipAddress))
            println("Server is listening on $ipAddress:$port")

            // Listen for incoming client connections
            while (true) {
                val clientSocket = serverSocket.accept()
                println("New client connected: ${clientSocket.inetAddress}")

                // Handle the client in a separate thread
                Thread {
                    try {
                        val input = BufferedReader(InputStreamReader(clientSocket.getInputStream()))
                        val output = PrintWriter(clientSocket.getOutputStream(), true)

                        // Read the incoming message from the client
                        val clientMessage = input.readLine()
                        println("Received message: $clientMessage")

                        // Respond back to the client
                        output.println("Hello from the server!")
                        println("Response sent to client.")
                    } catch (e: Exception) {
                        e.printStackTrace()
                    } finally {
                        clientSocket.close()
                        println("Client connection closed.")
                    }
                }.start()
            }
        } catch (e: Exception) {
            println("Error occurred: ${e.message}")
        }
    }
}

