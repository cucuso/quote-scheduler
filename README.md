# Moving Quote Request System - Full Stack Application

A simple full-stack moving company quote request application built with Spring Boot. For local development it can run with a file-based H2 database, and for Docker deployments it can run with PostgreSQL via Docker Compose.

## Features

- **Multi-language Support**:
  - English, Spanish, and French translations
  - Real-time language switching
  - URL parameter support (?lang=es or ?lang=en)

- **Comprehensive Quote Form**:
  - Editable company name
  - Language selector
  - Detailed furniture/item inventory (Bedroom, Hall, Dining Room, Kitchen, Others)
  - Quantity selector (0-5) for 60+ item types with radio buttons
  - Customer contact information (name, phone)
  - Pickup address with zipcode and access type
  - Delivery address with zipcode and access type
  - Tentative moving date and preferred time
  - Ad source tracking
  - Additional notes field

- **Modern Stripe-Inspired UI**:
  - Clean, professional design
  - Smooth animations and transitions
  - Fully responsive (mobile, tablet, desktop)
  - Interactive radio buttons with visual feedback
  - Success/error modals

- **Dashboard Features**:
  - View all quote requests
  - Filter quotes by date range
  - Delete quotes
  - Detailed view of all customer information and items

- **Backend**:
  - H2 database for simple local development
  - PostgreSQL via Docker Compose for persistent containerized deployments
  - JSON storage for item quantities
  - RESTful API endpoints
  - Automatic timestamp tracking

## Technology Stack

- **Backend**: Spring Boot 3.2.0
- **Database**: H2 (local fallback) or PostgreSQL (Docker Compose / production-friendly)
- **ORM**: Spring Data JPA / Hibernate
- **Frontend**: HTML, CSS, JavaScript (Vanilla) with Stripe-inspired design
- **Template Engine**: Thymeleaf
- **Internationalization**: Multi-language support (English, Spanish, French)

## API Endpoints

### Pages
- `GET /form` - Moving quote request form
- `GET /calendar/:companyName` - Quotes dashboard viewer

### REST API
- `POST /bookings` - Create a new moving quote request
- `GET /api/bookings?companyName=...&from=...&to=...` - Fetch quotes (with optional filters)
- `PUT /api/bookings/:id` - Update a quote
- `DELETE /api/bookings/:id` - Delete a quote

## Running the Application

### Prerequisites
- Java 17 or higher
- Maven 3.6+

### Docker Compose

Use Docker Compose when you want the app and a persistent database to come up together:

```bash
docker compose up --build
```

This starts:
- `app`: the Spring Boot application on `http://localhost:8080`
- `db`: a lightweight PostgreSQL database on `localhost:5432`

The PostgreSQL data lives in the named Docker volume `postgres_data`, so normal rebuilds and redeployments do not wipe your data.

Common commands:

```bash
# Start in background
docker compose up --build -d

# Stop containers without deleting database data
docker compose down

# Stop containers and delete database volume/data
docker compose down -v
```

Important note: your data remains safe across `docker compose up`, image rebuilds, and `docker compose down`. You only lose it if you explicitly remove the volume, such as with `docker compose down -v`, `docker volume rm`, or by deleting Docker's local volumes.

### Local Java Run

If you want to run the app directly without Docker, it falls back to the local H2 file database.

```bash
# Create executable JAR
mvn clean package

# Run the JAR
java -jar target/scheduler-1.0-SNAPSHOT.jar
```

The application will start on `http://localhost:8080`

### Docker Image Only

If you only want a single container and are okay with the app's built-in H2 fallback:

```bash
docker build -t scheduler .
docker run --rm -p 8080:8080 scheduler
```

This mode does not start PostgreSQL for you.

## Using the Application

### Submitting a Moving Quote Request
1. Navigate to `http://localhost:8080/form`
2. Fill in the quote request details:
   - Company Name
   - Language preference
   - Select quantities (0-4) for each furniture/item type across 5 categories:
     - Bedroom (beds, mattresses, dressers, etc.)
     - Hall (couches, tables, TV stands, etc.)
     - Dining Room (tables, chairs, sideboards, etc.)
     - Kitchen (refrigerator, freezer, microwave, etc.)
     - Others (boxes, office furniture, exercise equipment, etc.)
   - Customer information (name, phone)
   - Pickup address, zipcode, and access type
   - Delivery address, zipcode, and access type
   - Tentative moving date
   - Where you saw the ad
   - Any additional notes or special items
3. Click "Submit Quote Request"

### Viewing Quote Requests (Dashboard)
1. Navigate to `http://localhost:8080/calendar/YourCompanyName`
2. View all quote requests
3. Use date filters to narrow down results by tentative date
4. Delete quotes as needed
5. See detailed breakdown of items requested for each quote

### H2 Console (Development)
- Access the H2 database console at `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:file:./data/scheduler`
- Username: `sa`
- Password: (leave empty)

## Database

The application supports two database modes:

- H2 file database for simple non-Docker local development
- PostgreSQL for Docker Compose and production-style deployments

### Docker Compose Database

When running with Docker Compose, the app connects to PostgreSQL using:

- Host: `db` from inside Docker, `localhost` from your machine
- Port: `5432`
- Database: `scheduler`
- Username: `scheduler`
- Password: `scheduler`

Data is persisted in the Docker named volume `postgres_data`.

### Local H2 Database

When running without `DATABASE_URL`, the app falls back to H2 file persistence at `./data/scheduler.mv.db`.

### Quote Request Schema
```sql
CREATE TABLE bookings (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    company_name VARCHAR(255) NOT NULL,
    language VARCHAR(50) NOT NULL,
    items TEXT,                          -- JSON string of item quantities
    additional_items TEXT,
    full_name VARCHAR(255) NOT NULL,
    phone_number VARCHAR(50) NOT NULL,
    pickup_address VARCHAR(500) NOT NULL,
    pickup_zipcode VARCHAR(20) NOT NULL,
    pickup_access VARCHAR(100) NOT NULL,
    delivery_address VARCHAR(500) NOT NULL,
    delivery_zipcode VARCHAR(20) NOT NULL,
    delivery_access VARCHAR(100) NOT NULL,
    tentative_date DATE NOT NULL,
    ad_source VARCHAR(100) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP
);
```

## Deployment

### Deploy to Render / Railway / Heroku

1. Push your code to a Git repository
2. Create a new web service on your platform of choice
3. Set build command: `mvn clean package`
4. Set start command: `java -jar target/scheduler-1.0-SNAPSHOT.jar`
5. Environment variables:
   - `PORT=8080` (or as required by platform)
   - `DATABASE_URL=jdbc:postgresql://...`
   - `PGUSER=...`
   - `PGPASSWORD=...`

### Deploy to VPS

```bash
# Build the application
mvn clean package

# Copy JAR to server
scp target/scheduler-1.0-SNAPSHOT.jar user@yourserver:/path/to/app/

# SSH to server and run
ssh user@yourserver
cd /path/to/app
nohup java -jar scheduler-1.0-SNAPSHOT.jar &
```

## Project Structure

```
scheduler/
├── src/
│   ├── main/
│   │   ├── java/org/example/
│   │   │   ├── App.java              # Main Spring Boot application
│   │   │   ├── controller/
│   │   │   │   └── BookingController.java
│   │   │   ├── model/
│   │   │   │   └── Booking.java
│   │   │   └── repository/
│   │   │       └── BookingRepository.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── templates/
│   │           ├── form.html
│   │           └── calendar.html
│   └── test/
├── data/                              # H2 database files (auto-created)
├── pom.xml
└── README.md
```

## Configuration

Edit `src/main/resources/application.properties` to customize:
- Server port
- Database location and settings
- Logging levels
- H2 console access

## Features in Detail

### Comprehensive Item Inventory
The form includes 60+ common moving items organized into 5 categories, with quantity selectors (0-4) for each item.

### JSON Item Storage
Item quantities are stored as JSON in the database, making it easy to query and display detailed inventory information.

### Date Filtering
Filter quote requests by tentative moving date range using the dashboard interface.

### Responsive Design
The UI is mobile-friendly and adapts to different screen sizes, making it easy for customers to request quotes on any device.

### Data Persistence
For Docker Compose deployments, all quote requests are stored in PostgreSQL and survive normal container rebuilds and restarts because the database uses a persistent Docker volume. For non-Docker local development, data is persisted with H2's file-based storage.

### Multi-language Support
Track customer language preferences for better service delivery.

## Future Enhancements

Potential improvements:
- User authentication and authorization for company access
- Email notifications to company when new quote is submitted
- SMS notifications to customer with confirmation
- Automatic quote pricing calculator based on items and distance
- PDF export of quote details
- Quote status tracking (pending, reviewed, quoted, accepted, completed)
- Customer portal to track quote status
- Integration with mapping APIs for distance calculation
- Photo upload for special items
- Calendar integration for scheduling
- Payment/deposit processing

## Marketing Strategy: Brickell Miami Moving Market

### Target Audience
Brickell is high-rise condo central with heavy move-in/move-out traffic. Target young professionals (25-45) who value convenience and transparency.

### Facebook/Instagram Ads Strategy

**Targeting:**
- Location: Brickell + 3-5mi radius
- Age: 25-45 (young professionals)
- Life events: Recently moved, new job, engaged (people in transition)
- Interests: Apartment hunting, Zillow, real estate, "moving"
- Lookalike audience once you get some customers

**Ad Creative That Works:**
- "Moving in Brickell? Skip the hassle" — speak to pain points
- Show before/after of a move (video performs better)
- Price anchor: "Studios from $X, 1BR from $Y" — reduces friction
- Testimonials from real customers (even text screenshots)
- Carousel: "What we handle" — packing, heavy items, building coordination

**Hook Ideas:**
- "We know Brickell buildings" (elevator reservations, COIs, loading docks)
- "Same-day quotes via WhatsApp"
- "No hidden fees" — movers have a shady rep, lean into transparency

### Click-to-WhatsApp Flow
- Use Meta's Click-to-WhatsApp ad format directly
- Or link to your `/form?company=YourCompany` for more qualified leads
- Test both — form = higher intent, direct WhatsApp = more volume

### Budget to Start
- $20-30/day, test for 2 weeks
- Kill ads under 2% CTR, scale winners

### Bonus Moves
- Partner with Brickell leasing offices / concierges (referral fee)
- Google "movers brickell miami" — see if SEO/Google Ads worth it later
- Nextdoor posts (free, locals trust it)

## License

MIT License - feel free to use this for your projects!
