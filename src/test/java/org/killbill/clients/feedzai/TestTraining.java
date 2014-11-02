package org.killbill.clients.feedzai;

import org.joda.time.DateTime;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class TestTraining {


    private static final String[] CHILE_FIRST_NAMES = {
            "Camila",
            "Franco",
            "Francisca",
            "Alejandro",
            "Constanza",
            "Jorge",
            "javiera",
            "Sebastian",
            "Catalina",
            "Luis",
            "Paulina",
            "Patricio",
            "carla",
            "Valentina",
            "Francisco",
            "Tamara",
            "Wilson",
            "Macarena",
            "Rocio",
            "Cristian",
            "daniela",
            "Pablo",
            "Sofia",
            "Eduardo",
            "Liz",
            "Matias",
            "Carolina",
            "Alexis",
            "Nicole",
            "Vicente",
            "natalia",
            "David",
            "Maria Jesus",
            "Luis felipe",
            "Fernanda",
            "Parry",
            "Gabriela",
            "Arnold",
            "paula",
            "Nico",
            "maria",
            "Nicolas",
            "lorena",
            "Flavio",
            "Allyson",
            "Christopher",
            "Karina",
            "Alonso",
            "Renata",
            "Alexander Mraz",
            "pia",
            "FELIPE GOYE",
            "victoria",
            "Fernando",
            "analaura",
            "hector adrian",
            "Ivi",
            "Martina",
            "Lucy",
            "daniel",
            "Vanesa",
            "vania",
            "kira",
            "alberto",
            "Ferna",
            "lucas",
            "andrea",
            "Alejandro Miranda",
            "Valentina Quezada",
            "José Luis",
            "Thalia",
            "Keila",
            "Tatyana",
            "Marta",
            "jonathan",
            "Maria Jose",
            "abraham",
            "flor",
            "Mauricio",
            "Karolina",
            "carlos",
            "antonia",
            "Sue",
            "Marcela",
            "Alfredo",
            "Nicol",
            "Felipe",
            "Bea",
            "Felipe",
            "Nayaret",
            "bastian",
            "Carmen",
            "Inaki",
            "Ela",
            "Walter",
            "soledad venegas",
            "handricars",
            "alejandra",
            "Paulo",
            "mitzi",
            "panchito",
            "Koniie",
            "Alex Eduardo",
            "Jennyfer",
            "Felipe Montanares",
            "Dita",
            "Kevin",
            "Daphne Moyano",
            "Victor",
            "simonett adriana",
            "Daniel Celsi",
            "Maria jose",
            "Armando",
            "Kata",
            "leandro",
            "Nathaly",
            "BenjaGotter",
            "romina",
            "jovita",
            "yoyi",
            "natuscka espinoza",
            "Javier",
            "Nora",
            "José",
            "edna",
            "yender",
            "de",
            "Zhang",
            "Fabi",
            "Ignacio",
            "KELLYTTO",
            "Philip",
            "Ivonne",
            "Ruben",
            "trinidad",
            "René",
            "fran",
            "Joaquin",
            "Tania",
            "sergio",
            "Milla",
            "Hernán",
            "riley",
            "Feña",
            "mario",
            "Maria paz",
            "Gonzalo",
            "Naty",
            "Diego",
            "sasha",
            "Kira Otaku",
            "Nancy",
            "Pablo Zúñiga",
            "paulinna",
            "emilio",
            "yohana",
            "Poppy"
    };

    private static final String[] CHILE_LAST_NAMES = {
            "Fernandez",
            "Rodriguez",
            "Gonzalez",
            "Garcia",
            "Lopez",
            "Martinez",
            "Perez",
            "Alvarez",
            "Gomez",
            "Sanchez",
            "Diaz",
            "Vasquez",
            "Castro",
            "Romero",
            "Suarez",
            "Blanco",
            "Ruiz",
            "Alonso",
            "Torres",
            "Dominguez",
            "Gutierrez",
            "Sosa",
            "Iglesias",
            "Gimenez",
            "Ramirez",
            "Martin",
            "Varela",
            "Ramos",
            "Nunez",
            "Rossi",
            "Silva",
            "Mendez",
            "Hernandez",
            "Flores",
            "Pereyra",
            "Ferrari",
            "Ortiz",
            "Medina",
            "Benitez",
            "Herrera",
            "Arias",
            "Acosta",
            "Moreno",
            "Aguirre",
            "Otero",
            "Cabrera",
            "Rey",
            "Rojas",
            "Vidal",
            "Molina",
            "Russo",
            "Paz",
            "Vega",
            "Costa",
            "Bruno",
            "Romano",
            "Morales",
            "Rios",
            "Miranda",
            "Munoz",
            "Franco",
            "Castillo",
            "Campos",
            "Bianchi",
            "Luna",
            "Correa",
            "Ferreyra",
            "Navarro",
            "Quiroga",
            "Colombo",
            "Cohen",
            "Pereyra",
            "Vera",
            "Lorenzo",
            "Gil",
            "Santos",
            "Delgado",
            "Godoy",
            "Rivas",
            "Rivero",
            "Gallo",
            "Peralta",
            "Soto",
            "Figueroa",
            "Juarez",
            "Marino",
            "Ponce",
            "Calvo",
            "Ibanez",
            "Caceres",
            "Carrizo",
            "Vargas",
            "Mendoza",
            "Aguilar",
            "Ledesma",
            "Guzman",
            "Soria",
            "Villalba",
            "Prieto"
    };


    private final static Location[] CHILE_LOCATIONS = {
            new Location("Santigo", "CL", "7550006", "152.139.10.00"),
            new Location("Santigo", "CL", "7550006", "152.139.128.00"),
            new Location("Santigo", "CL", "7550006", "152.139.34.00"),
            new Location("Santigo", "CL", "7550006", "163.247.78."),
            new Location("Santigo", "CL", "7550006", "181.160.17.00"),
            new Location("Santigo", "CL", "7550006", "186.10.128.00"),
            new Location("Santigo", "CL", "7550006", "186.103.129.00"),
            new Location("Santigo", "CL", "7550006", "146.83.17.00"),
            new Location("Antofagasta", "CL", "1240000", "146.83.128.00"),
            new Location("Concepcion", "CL", "4030000", "152.74.12.00")
    };

    private final static Location[] CHINA_LOCATIONS = {
            new Location("Jinan", "CN", "250400", "58.59.68.91"),
            new Location("Langfang", "CN", "065900", "124.42.127.221"),
            new Location("Guangzhou", "CN", "518000", "14.220.48.253"),
            new Location("Guangzhou", "CN", "518000", "59.37.163.176"),
            new Location("Shanghai", "CN", "201900", "61.172.238.178")
    };

    private final static Location[] EUROPE_LOCATIONS = {
            new Location("Paris", "FR", "75000", "195.154.120.129"),
            new Location("Lyon", "FR", "69000", "unknown"),
            new Location("Grenoble", "FR", "38564", "unknown"),
            new Location("Toulouse", "FR", "31400", "unknown"),
            new Location("Strasbourg", "FR", "67100", "unknown")
    };


    private Random random;

    private DefaultFeedzaiClient client;

    @BeforeClass(groups = "slow")
    public void beforeTest() {
        final String apiKey = System.getProperty("org-kill-bill.clients.feedzai.api.key");
        Assert.assertNotNull(apiKey, "Need to specify client API KEY");
        client = new DefaultFeedzaiClient(true, apiKey, true);
        random = new Random();

    }



    @Test(groups = "slow")
    public void generateGoodData() throws FeedzaiClientException {


        final List<Location> genIPChileLocation = new ArrayList<Location>();
        fillLocation(genIPChileLocation);

        int nbIterations = 500;

        while (nbIterations > 0) {
            final String userId = "gen_good_" + random.nextLong();
            final String paymentId = "gen_good_pay_" + random.nextLong();
            final Location location = genIPChileLocation.get(random.nextInt(genIPChileLocation.size())); //CHILE_LOCATIONS[random.nextInt(CHILE_LOCATIONS.length)];
            final String firstName = CHILE_FIRST_NAMES[random.nextInt(CHILE_FIRST_NAMES.length)];
            final String lastName = CHILE_LAST_NAMES[random.nextInt(CHILE_LAST_NAMES.length)];
            final String fullName = firstName + " " + lastName;

            final String gender = random.nextInt(2) == 0 ? "F" : "M";
            final String dateOfBirth = "1972/08/07";
            final Map<String, String> userDefined = new HashMap<String, String>();
            userDefined.put("FEEDZAI_BILLING_USER_DEFINED_USER_AGENT", "Chrome");
            userDefined.put("FEEDZAI_BILLING_USER_DEFINED_ACCEPT", "application/html\n");

            final PaymentRequest request = new PaymentRequest(userId,
                    2999,
                    location.ip,
                    paymentId,
                    "sale",
                    firstName + lastName + "@killbill.io",
                    fullName,
                    null,
                    gender,
                    dateOfBirth,
                    null,
                    null,
                    null,
                    location.zip,
                    location.city,
                    null,
                    location.country,
                    null,
                    fullName,
                    "07/17",
                    location.country,
                    getCardBin(),
                    getLast4(),
                    null,
                    null,
                    null,
                    location.zip,
                    location.city,
                    null,
                    location.country,
                    location.country,
                    userDefined);
            try {
                final PaymentResponse result = client.scorePayment(request);
                System.out.println(String.format("-> Score: %s [ITERATION %d]", result.getScore(), nbIterations));

                client.labelPreviousPayment(paymentId, new LabelRequest("ok"));
                nbIterations--;
            } catch (Exception ignore) {
                System.out.println("Failed, skipping");
            }
        }
    }


    @Test(groups = "slow")
    public void generateBadData() throws FeedzaiClientException {

        int nbIterations = 100;

        while (nbIterations > 0) {
            final String userId = "gen_bad_" + random.nextLong();
            final String paymentId = "gen_bad_pay_" + random.nextLong();
            final Location chinaLocation = CHINA_LOCATIONS[random.nextInt(CHINA_LOCATIONS.length)];
            final Location chileLocation = CHILE_LOCATIONS[random.nextInt(CHILE_LOCATIONS.length)];
            final Location europeLocation = EUROPE_LOCATIONS[random.nextInt(EUROPE_LOCATIONS.length)];

            final String firstName = "Than";
            final String lastName = random.nextInt(2) == 0 ? "Dupond" : "Dupont";
            final String fullName = firstName + " " + lastName;

            final String chileFirstName = CHILE_FIRST_NAMES[random.nextInt(CHILE_FIRST_NAMES.length)];
            final String chileLastName = CHILE_LAST_NAMES[random.nextInt(CHILE_LAST_NAMES.length)];
            final String chileFullName = chileFirstName + " " + chileLastName;


            final String gender = random.nextInt(2) == 0 ? "F" : "M";
            final String dateOfBirth = "1970/07/08";
            final Map<String, String> userDefined = new HashMap<String, String>();
            userDefined.put("FEEDZAI_BILLING_USER_DEFINED_USER_AGENT", "Chrome");
            userDefined.put("FEEDZAI_BILLING_USER_DEFINED_ACCEPT", "application/html\n");

            final PaymentRequest request = new PaymentRequest(userId,
                    2999,
                    chinaLocation.ip,
                    paymentId,
                    "sale",
                    chileFirstName +  chileLastName + "@yahoo.com",
                    chileFullName,
                    null,
                    gender,
                    dateOfBirth,
                    null,
                    null,
                    null,
                    chileLocation.zip,
                    chileLocation.city,
                    null,
                    chileLocation.country,
                    null,
                    chileFullName,
                    "07/16",
                    europeLocation.country,
                    getCardBin(),
                    getLast4(),
                    null,
                    null,
                    null,
                    europeLocation.zip,
                    europeLocation.city,
                    null,
                    europeLocation.country,
                    userDefined);

            try {
                final PaymentResponse result = client.scorePayment(request);
                System.out.println(String.format("-> Score: %s [ITERATION %d]", result.getScore(), nbIterations));

                client.labelPreviousPayment(paymentId, new LabelRequest("fraud"));
                nbIterations--;
            } catch (Exception ignore) {
                System.out.println("Failed, skipping");
            }
        }
    }


    private static int nextIp(final String ip) {
        final String[] parts = ip.split("\\.");
        return ((Integer.parseInt(parts[0], 10) << (8 * 3)) & 0xFF000000 |
                (Integer.parseInt(parts[1], 10) << (8 * 2)) & 0x00FF0000 |
                (Integer.parseInt(parts[2], 10) << (8 * 1)) & 0x0000FF00 |
                (Integer.parseInt(parts[3], 10) << (8 * 0)) & 0x000000FF) + 1;
    }

    private static String toString(final int ip) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 3; i >= 0; --i) {
            sb.append((ip >> (i * 8)) & 0x000000FF);
            if (i != 0) {
                sb.append(".");
            }
        }

        return sb.toString();
    }


    private Integer getCardBin() {
        return 400000 + random.nextInt(100000);
    }

    private Integer getLast4() {
        return random.nextInt(10000);
    }

    private static void fillLocation(List<Location> genIPChileLocation) {
        int nextIp = nextIp("146.83.0.0");
        String nextIpString = toString(nextIp);
        while (!"146.83.255.255".equals(nextIpString)) {
            genIPChileLocation.add(new Location("Santigo", "CL", "7550006", nextIpString));
            nextIp = nextIp(nextIpString);
            nextIpString = toString(nextIp);
        }
    }

    private static class Location {
        public String city;
        public String country;
        public String zip;
        public String ip;

        private Location(String city, String country, String zip, String ip) {
            this.ip = ip;
            this.city = city;
            this.zip = zip;
            this.country = country;
        }
    }
}
