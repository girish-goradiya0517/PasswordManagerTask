<h1>🔐 Password Manager App</h1>

<p>A secure and modern Android app built using <strong>Kotlin</strong>, <strong>Jetpack Compose</strong>, and <strong>Material Design 3</strong>. It helps users safely store and manage their credentials with full local encryption and intuitive design.</p>

<hr/>

<h2>✅ Functional Requirements</h2>

<ul>
  <li><strong>Add Password</strong>
    <ul>
      <li>Securely add new passwords by entering account name, email/username, and password.</li>
    </ul>
  </li>
  <li><strong>View/Edit Password</strong>
    <ul>
      <li>View and update saved credentials easily.</li>
    </ul>
  </li>
  <li><strong>Show List of Passwords</strong>
    <ul>
      <li>Display all saved passwords on the home screen.</li>
    </ul>
  </li>
  <li><strong>Delete Password</strong>
    <ul>
      <li>Delete saved passwords permanently.</li>
    </ul>
  </li>
</ul>

<h2>⚙️ Technical Requirements</h2>

<ul>
  <li><strong>Encryption</strong>
    <ul><li>Secure storage with AES/local encryption.</li></ul>
  </li>
  <li><strong>Database</strong>
    <ul><li>Uses Room DB for secure, local persistence.</li></ul>
  </li>
  <li><strong>User Interface</strong>
    <ul><li>Modern UI using Jetpack Compose and Material 3.</li></ul>
  </li>
  <li><strong>Input Validation</strong>
    <ul><li>Ensures fields are properly filled.</li></ul>
  </li>
  <li><strong>Error Handling</strong>
    <ul><li>Graceful error messages and fallback flows.</li></ul>
  </li>
</ul>

<h2>🌟 Bonus Features</h2>

<ul>
  <li><strong>Biometric or PIN Authentication</strong>
    <ul><li>Secure access control on app launch.</li></ul>
  </li>
  <li><strong>Password Strength Meter</strong>
    <ul><li>Visual strength indicator while typing password.</li></ul>
  </li>
  <li><strong>Password Generator</strong>
    <ul><li>Generate secure, random passwords instantly.</li></ul>
  </li>
</ul>

<h2>🧰 Tech Stack Used</h2>

<ul>
  <li><strong>Language</strong>: Kotlin with Coroutines + Flow</li>
  <li><strong>UI</strong>: Jetpack Compose</li>
  <li><strong>Navigation</strong>: Voyager</li>
  <li><strong>State Management</strong>: ViewModel + MVI</li>
  <li><strong>Storage</strong>: Room DB</li>
  <li><strong>DI</strong>: Koin</li>
  <li><strong>Serialization</strong>: kotlinx-serialization</li>
  <li><strong>Images</strong>: Coil</li>
  <li><strong>HTML Parsing</strong>: Jsoup</li>
  <li><strong>Min SDK</strong>: 24</li>
</ul>

<h2>🚀 How to Run the App</h2>

<h4>Install Prerequisites</h4>
<ul>
  <li>Android Studio (latest)</li>
  <li>Android SDK 24+</li>
  <li>Kotlin plugin (bundled in Android Studio)</li>
</ul>

<h4>Clone the Project</h4>
<ul>
  <li>Open Android Studio</li>
  <li>Click on <code>Get from VCS</code></li>
  <li>Paste the GitHub repo URL</li>
  <li>Click <code>Clone</code></li>
</ul>

<h4>Run the App</h4>
<ul>
  <li>Press <code>Shift + F10</code> or click the green <strong>Run</strong> button</li>
  <li>Select your device or emulator</li>
  <li>The app will build and install automatically</li>
</ul>

<h2>🔒 Security Notes</h2>

<ul>
  <li>All password data is encrypted and stored only on the device</li>
  <li>App launch is protected with biometric or PIN authentication</li>
  <li>No passwords are sent over the internet or stored in the cloud</li>
</ul>

<h2>🤝 Contributions</h2>

<ul>
  <li>Fork the repository</li>
  <li>Create a new branch (e.g. <code>feature/your-feature</code>)</li>
  <li>Commit your changes and push</li>
  <li>Submit a Pull Request</li>
</ul>
