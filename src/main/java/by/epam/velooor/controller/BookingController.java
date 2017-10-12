package by.epam.velooor.controller;


import by.epam.velooor.entity.Apartment;
import by.epam.velooor.exception.ControllerException;
import by.epam.velooor.exception.LogicException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import by.epam.velooor.logic.BookingLogic;

@RestController
@RequestMapping("/hotel")
public class BookingController {

    private static Logger logger = LogManager.getLogger(BookingController.class);
    private static final String MEDIA_TYPE_JSON = "application/json";

    @Autowired
    private BookingLogic bookingLogic;

    @PostMapping
    public void bookApartment(@RequestParam("userId") int userId, @RequestParam("apartmentId") int apartmentId) throws ControllerException {
        try {
            bookingLogic.bookApartment(userId, apartmentId);
        } catch (LogicException e) {
            throw new ControllerException(e);
        }
    }


    @PostMapping(consumes = MEDIA_TYPE_JSON)
    public void bookApartments(@RequestParam("userId") int userId, @RequestBody Apartment[] apartments) throws ControllerException {
        try {
            bookingLogic.bookApartments(userId, apartments);
        } catch (LogicException e) {
            throw new ControllerException(e);
        }
    }

    @DeleteMapping
    public void freeApartment(@RequestParam("userId") int userId, @RequestParam("apartmentId") int apartmentId) throws ControllerException {
        try {
            bookingLogic.freeApartment(userId, apartmentId);
        } catch (LogicException e) {
            throw new ControllerException(e);
        }
    }

    @DeleteMapping(consumes = MEDIA_TYPE_JSON)
    public void freeApartment(@RequestParam("userId") int userId, @RequestBody Apartment apartment) throws ControllerException {
        try {
            bookingLogic.freeApartment(userId, apartment);
        } catch (LogicException e) {
            throw new ControllerException(e);
        }
    }

    @GetMapping(produces = MEDIA_TYPE_JSON)
    public Object[][] getBookingInfo() {
        return bookingLogic.getBookingInfo();
    }
}
