//package com.exadel.belarusattractions.generators;
//
//import com.exadel.belarusattractions.dto.sights.Coordinates;
//import com.exadel.belarusattractions.dto.sights.Photo;
//import com.exadel.belarusattractions.dto.sights.Sight;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Demo. This object generates some sights to show application.
// * <p/>
// * Developer: Yan Khonskiy
// * Created: 11:10 AM, 11/9/12
// */
//@Deprecated
//public class AttractionsGenerator {
//
//    private List<Sight> sights;
//
//    public AttractionsGenerator() {
//        init();
//    }
//
//    private void init() {
//        sights = new ArrayList<Sight>();
//        initSights();
//    }
//
//    private void initSights() {
//        List<Photo> gallery = new ArrayList<Photo>();
//
//        gallery.add(initPhoto("http://www.app.by/img/photo/gallery/nl-01.jpg", "library-view"));
//        gallery.add(initPhoto("http://www.app.by/img/photo/gallery/nl-02.jpg", "library-view"));
//        gallery.add(initPhoto("http://www.app.by/img/photo/gallery/nl-03.jpg", "library-view"));
//        gallery.add(initPhoto("http://www.app.by/img/photo/gallery/nl-04.jpg", "library-view"));
//        sights.add(
//                initSight(0L,
//                        initCoordinates(53.931477, 27.646353),
//                        "National Library of Belarus",
//                        "The National Library of Belarus, founded on 15 September 1922, is a copyright library of the Republic of Belarus.",
//                        "http://en.wikipedia.org/wiki/National_Library_of_Belarus",
//                        "http://www.app.by/img/photo/gallery/nl-01.jpg",
//                        "national-library",
//                        "Nezavisimosti ave. 116, Minsk, Belarus",
//                        "The National Library of Belarus, founded on 15 September 1922, is a copyright library of the Republic of Belarus. It houses the largest collection of Belarusian printed materials and the third largest collection of books in Russian behind the Russian State Library (Moscow) and the Russian National Library (St Petersburg).\n" +
//                                "It is now located in a new 72-metre (236 feet) high building in Minsk, Belarus. The building has 22 floors and was completed in January 2006. The building can seat about 2,000 readers and features a 500-seat conference hall. Its main architectural component has the shape of a rhombicuboctahedron. The library's new building was designed by architects Mihail Vinogradov and Viktor Kramarenko and opened on 16 June 2006.\n" +
//                                "The National Library of Belarus is the main information and cultural centre of the country. Its depository collections include 8 million items of various media. In 1993 the National Library of Belarus started to create its own electronic information resources. It has generated a collection of bibliographic, factual graphic, full-text, graphic, sound and language databases that comprise more than 2 million records. The scope of databases is quite wide: humanities, social sciences, history, art and culture of Belarus. Library users also have access to databases of other libraries and academic institutions, including foreign ones.\n" +
//                                "The library service is in great demand. More than 90 thousand citizens of Belarus are library users, who annually request 3.5 million documents. Every day the library is visited by more than 2,200 people. The library delivers about 12,000 documents daily.\n" +
//                                "In addition to serving as a functional library, the National Library is a city attraction. It is situated in a park on a river bank and has an observation deck looking over Minsk. As of 2009 it is the only structure in Minsk with a public observation deck. The area in front of the library is used for many public concerts and shows.\n" +
//                                "The building is also the subject of an art video by French artist Raphael Zarka, \"Rhombus Sectus\", shown at the Bischoff/Weiss gallery, London, in 2011.",
//                        gallery));
//        gallery = new ArrayList<Photo>();
//        gallery.add(initPhoto("http://www.app.by/img/photo/gallery/th-01.jpg", "trinity-hill-view"));
//        gallery.add(initPhoto("http://www.app.by/img/photo/gallery/th-02.jpg", "trinity-hill-view"));
//        gallery.add(initPhoto("http://www.app.by/img/photo/gallery/th-03.jpg", "trinity-hill-view"));
//        sights.add(
//                initSight(1L,
//                        initCoordinates(53.908826, 27.556025),
//                        "Trinity Hill",
//                        "The Trinity Hill is the oldest surviving district of Minsk, although it's not part of the downtown, rather a suburb.",
//                        "http://en.wikipedia.org/wiki/Trinity_Hill",
//                        "http://www.app.by/img/photo/gallery/th-01.jpg",
//                        "trinity-hill",
//                        "Trinity Hill, Minsk, Belarus",
//                        "The Trinity Hill is the oldest surviving district of Minsk, although it's not part of the downtown, rather a suburb, hence another name, Trinity Banlieu (Trojeckaje Pradmiescie). The historic neighbourhood sprawls along the left bank of the Svislach River in the southeastern part of the modern city. The Belarusian 5-ruble bill features an image of Trayetskaye Pradmestsye.\n" +
//                                "The district takes its name from the Trinity Convent, of which little remains. The first Roman Catholic church in Minsk, the Ascension Monastery, the church of Sts. Boris and Gleb, and a synagogue have also disappeared. This is an area of 19th-century housing, mostly restored after the ravages of World War II. Modern buildings include the national opera and ballet theatre and the Island of Tears memorial.",
//                        gallery));
//        gallery = new ArrayList<Photo>();
//        gallery.add(initPhoto("http://www.app.by/img/photo/gallery/rc-01.jpg", "church-view"));
//        gallery.add(initPhoto("http://www.app.by/img/photo/gallery/rc-02.jpg", "church-view"));
//        gallery.add(initPhoto("http://www.app.by/img/photo/gallery/rc-03.jpg", "church-view"));
//        sights.add(
//                initSight(2L,
//                        initCoordinates(53.896683, 27.547444),
//                        "Church of Saints Simon and Helena",
//                        "Church of Saints Simon and Helen also known as the Red Church is a Roman Catholic church on Independence Square in Minsk, Belarus.",
//                        "http://en.wikipedia.org/wiki/Church_of_Saints_Simon_and_Helena",
//                        "http://www.app.by/img/photo/gallery/rc-01.jpg",
//                        "church-of-saints-simon-helena",
//                        "Sovetskaya str. 15, Minsk, Belarus",
//                        "Church of Saints Simon and Helen also known as the Red Church is a Roman Catholic church on Independence Square in Minsk, Belarus.\n" +
//                                "This neo-Gothic church was designed by polish architects Tomasz Pajzderski and Wladyslaw Marconi, and built during 1905-1910. The bricks for its walls were sourced from Czestochowa, whilst the roof tiles came from Wloclawek. Its construction was financed by Edward Woynillowicz, a prominent Polish civic activist. The church was named and consecrated in memory of Woynillowicz's deceased children, Szymon and Helena.",
//                        gallery));
//        gallery = new ArrayList<Photo>();
//        gallery.add(initPhoto("http://www.app.by/img/photo/gallery/ln-01.jpg", "lake-narach-view"));
//        gallery.add(initPhoto("http://www.app.by/img/photo/gallery/ln-02.jpg", "lake-narach-view"));
//        gallery.add(initPhoto("http://www.app.by/img/photo/gallery/ln-03.jpg", "lake-narach-view"));
//        sights.add(
//                initSight(3L,
//                        initCoordinates(54.85764, 26.773968),
//                        "Lake Narach",
//                        "Lake Narach is a lake in north-western Belarus (Myadzyel Raion, Minsk Region), located in the basin of the Viliya river. It is the largest lake in Belarus (in 1921–39 it was the largest lake of Poland).",
//                        "http://en.wikipedia.org/wiki/Lake_Narach",
//                        "http://www.app.by/img/photo/gallery/ln-01.jpg",
//                        "lake-narach",
//                        "Myadzyel Raion, Belarus",
//                        "Lake Narach is a lake in north-western Belarus (Myadzyel Raion, Minsk Region), located in the basin of the Viliya river. It is the largest lake in Belarus (in 1921-39 it was the largest lake of Poland).\n" +
//                                "Narach is a part of the Narach lake group (the others being Miastra, Batoryn, Blednaje). It was formed about 11 thousand years ago after the Pleistocene ice ages. It has a surface area of 79.6 km2, a wider length of 12.8 km, a maximum depth of 24.08 m, a volume of 710 million cubic meters. The lake is surrounded with pine forests. The Narach River flows out of it.\n" +
//                                "Narach is an abode of 22 genera of fish, as the eel, pike, burbot, etc. The shore and islets are nested by different birds, as the mute swan, fish hawk, tarrock, dabchick.",
//                        gallery));
//        sights.add(
//                initSight(4L,
//                        initCoordinates(52.083047, 23.653305),
//                        "Brest Fortress",
//                        "It is one of the most important Soviet World War II war monuments commemorating the Soviet resistance against the German invasion on June 22, 1941 (Operation Barbarossa).",
//                        "http://en.wikipedia.org/wiki/Brest_Fortress",
//                        "http://www.app.by/img/photo/gallery/brf-01.jpg",
//                        "brest-fortress",
//                        "Brest, Belarus",
//                        "Brest Fortress, formerly known as Brest-Litovsk Fortress, is a 19th century Russian fortress in Brest, Belarus. It is one of the most important Soviet World War II war monuments commemorating the Soviet resistance against the German invasion on June 22, 1941 (Operation Barbarossa). Following the war, in 1965 the title Hero-Fortress was given to the Fortress to commemorate the heroic defence of the frontier stronghold during the very first weeks of the German-Soviet War. It was then part of the Byelorussian SSR. The title Hero-Fortress corresponds to the title Hero City, that was awarded to an eventual total of twelve Soviet cities.",
//                        null));
//        sights.add(
//                initSight(5L,
//                        initCoordinates(53.128302, 29.248448),
//                        "Babruysk fortress",
//                        "It is one of the best surviving examples of fortification architecture and design in the first half of the 19th century.",
//                        "http://en.wikipedia.org/wiki/Babruysk_fortress",
//                        "http://www.app.by/img/photo/gallery/bf-01.jpg",
//                        "babruysk-fortress",
//                        "Babruysk, Belarus",
//                        "The Babruysk Fortress is a historic fortress in the city of Babruysk, Belarus that was built between 1810 and 1836. It is one of the best surviving examples of fortification architecture and design in the first half of the 19th century. The fortress was constructed in the historic center of the city, at the confluence of the Babruyka and Berezina rivers. It was one of the western Russian fortresses.\n" +
//                                "In 1810, Tsar Alexander I sent out his military engineer Teodor Narbutt to find a site suitable for building a fortress somewhere on the Dnieper, between Mogilev and Rogachev in order to prepare for the looming threat in Western Europe. However, after his investigation, Narbut advised his superiors that a more strategic position would be on the shore of the Berezina river near Babruysk. This decision was approved by the Chief of Military Engineers, Count Carl Operman, who at the time had authority over all of Russian forts. On June 4, 1810, the Tsar issued an order for the Babruysk fortress to be constructed. Narbut had to resign for health reasons and was replaced by General Major Gabriel Ignatiev.\n" +
//                                "The early fortress comprised five bastions, multiple soil ridges, and water channels. The basis for the Babruysk fortress was the Babruysk Jesuit Monastery and a smaller Polish fortress, which were built earlier.\n" +
//                                "Only partially completed, the fortress had to face Napoleon's invading army in the summer of 1812. After the French army captured Minsk, General Ignatiev took command of the fort and the city of Babruysk, which served as a holdout for the retreating Russian forces. Soldiers from The Second Russian Army were provided with food and the wounded received medical treatment. After that they were ferried by the Berezina and Dniper to Smolensk, where the main Russian army was stationed. General Ignatiev remained in the fortress and oversaw its defence.\n" +
//                                "The city faced an attack by the forces of General Dombrovski, the Polish Corps Commander of Napoleon's Army. The siege lasted for four months, however the fortress held until the French forces began their retreat. Throughout this time Ignatiev was instrumental in collecting intelligence and forwarding it to the high command of the Russian army.\n" +
//                                "Following the Napoleonic wars, in 1820, the fortress was further rapidly expanded by the addition of 18 more bastions and towers. The fort Freidrich Wilhelm was designed according to the plans of the architect A. Staubert in 1822. Tsar Alexander I himself and his brother arrived in Babruysk on September 24, 1825 at the completion of this building phase.\n" +
//                                "By 1900 the fortress lost its military significance and was converted into a jail, used by Polish occupation forces (1919—1920, about 1,000 deaths) and used for concentration camp by German occupation forces (WW2, about 80,000 deaths).\n" +
//                                "Today, the Babruysk Fortress is registered as a national architectural monument of Belarus. However, the ruins of the Bobruysk fortress were removed in 2008 when the Bobruysk Ice Palace was erected",
//                        null));
//    }
//
//    private Sight initSight(
//            Long id,
//            Coordinates coordinates,
//            String title,
//            String prompt,
//            String detailedInformationUrl,
//            String iconUrl,
//            String name,
//            String address,
//            String detailedInformation,
//            List<Photo> gallery) {
//        Sight sight = new Sight();
//        sight.setId(id);
//        sight.setCoordinates(coordinates);
//        sight.setName(title);
//        sight.setShortDescription(prompt);
//        sight.setPictogramUrl(iconUrl);
//        sight.setCode(name);
//        sight.setAddress(address);
//        sight.setLongDescription(detailedInformation);
//        sight.setGallery(gallery);
//        return sight;
//    }
//
//    private Coordinates initCoordinates(double latitude, double longitude) {
//        Coordinates coordinates = new Coordinates();
//        coordinates.setLatitude(latitude);
//        coordinates.setLongitude(longitude);
//        return coordinates;
//    }
//
//    private Photo initPhoto(String url, String title){
//        return new Photo(url, title);
//    }
//
//    public List<Sight> getSights() {
//        return sights;
//    }
//
//    public Sight getSightById(long id) {
//        return sights.get((int) id);
//    }
//
//    public Sight getSightByName(String name) {
//        Sight foundSight = null;
//        for (Sight sight : sights) {
//            if (sight.getCode().equals(name)) {
//                foundSight = sight;
//            }
//        }
//        return foundSight;
//    }
//}