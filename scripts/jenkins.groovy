import com.cloudbees.plugins.credentials.*
import com.cloudbees.plugins.credentials.impl.*
import com.cloudbees.jenkins.plugins.sshcredentials.impl.*
import org.jenkinsci.plugins.plaincredentials.impl.*

def creds = com.cloudbees.plugins.credentials.CredentialsProvider.lookupCredentials(
    com.cloudbees.plugins.credentials.common.StandardCredentials.class,
    Jenkins.instance,
    null,
    null
)

creds.each { c ->
    println("ID: ${c.id}")
    println("Description: ${c.description}")

    if (c instanceof UsernamePasswordCredentialsImpl) {
        println("Type: Username/Password")
        println("Username: ${c.username}")
        println("Password: ${c.password?.plainText}")
    } else if (c instanceof StringCredentialsImpl) {
        println("Type: Secret Text")
        println("Secret: ${c.secret?.plainText}")
    } else if (c instanceof BasicSSHUserPrivateKey) {
        println("Type: SSH Key")
        println("Username: ${c.username}")
        println("Private Key: ${c.privateKey}")
    } else if (c instanceof FileCredentialsImpl) {
        println("Type: Secret File")
        println("Filename: ${c.fileName}")
    }
    println("---")
}
