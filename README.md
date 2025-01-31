# Scarlet Lifeline - Blood Donation Application 🩸  

A **full-stack blood donation management system** that connects **donors** with **blood banks and hospitals**, ensuring real-time inventory tracking and secure user management.  

## 🚀 Features  
- 🩸 **Donor Registration & Management** – Users can register as donors and update their availability.  
- 🏥 **Blood Bank Inventory Tracking** – Tracks available blood units in real time.  
- 🔐 **Secure Authentication** – Uses **BCrypt** for password hashing and **Spring Security** for role-based access.  
- 📅 **Donation Scheduling** – Allows users to schedule donation appointments.  
- 📊 **Admin Dashboard** – View donor statistics, manage inventory, and generate reports.  

## 🛠 Tech Stack  

### **Backend:**  
- **Java (Spring Boot)** – RESTful API development  
- **MongoDB** – NoSQL database for managing donor and inventory data  
- **Spring Security** – Secure authentication and role-based access control  
- **BCrypt** – Secure password encryption  

### **Frontend:**  
- **React.js** – Interactive UI for users and admins  

### **DevOps & Tools:**  
- **Docker** – Containerized deployment  
- **GitHub Actions** – CI/CD pipeline  
- **Postman** – API testing  
 

## 🔧 Setup & Installation  

### 1️⃣ Clone the Repository  
\`\`\`bash
git clone https://github.com/your-username/scarlet-lifeline.git
cd scarlet-lifeline
\`\`\`

### 2️⃣ Backend Setup  
\`\`\`bash
cd backend
mvn clean install
mvn spring-boot:run
\`\`\`

### 3️⃣ Frontend Setup  
\`\`\`bash
cd frontend
npm install
npm start
\`\`\`

## 📝 API Endpoints  

| Method | Endpoint | Description |
|--------|---------|------------|
| \`POST\` | \`/api/donors/register\` | Register a new donor |
| \`GET\`  | \`/api/bloodbanks/inventory\` | Get blood inventory details |
| \`POST\` | \`/api/donations/schedule\` | Schedule a donation appointment |
| \`GET\`  | \`/api/admin/stats\` | Fetch admin dashboard statistics |

