package com.bdabalcarce.demo.controller;
import com.bdabalcarce.demo.Dto.CompanyDto;
import com.bdabalcarce.demo.Dto.DonationDto;
import com.bdabalcarce.demo.Dto.UserDto;
import com.bdabalcarce.demo.entity.Company;
import com.bdabalcarce.demo.entity.Donation;
import com.bdabalcarce.demo.entity.Message;
import com.bdabalcarce.demo.entity.User;
import com.bdabalcarce.demo.service.DonationS;
import com.bdabalcarce.demo.service.UserS;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@CrossOrigin(origins = "https://bda-balcarce.netlify.app")
@RequestMapping({"/donations"})
public class DonationContr {
    @Autowired
    DonationS donationS;
    @Autowired UserS userS;
    @GetMapping("/list")
    public ResponseEntity<List<Donation>>list() {
        List<Donation> list = donationS.list();
        return new ResponseEntity(list, HttpStatus.OK);
    };
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DonationDto dtoDonation) {

        UserDto userdto = dtoDonation.getUser();
        CompanyDto companyDto = dtoDonation.getCompany();
        User user = null;
        Company company = null;
        // Si se detectan datos en user, se generará un objeto, y se asociará con su donation.
        if (userdto != null) {
            user = new User(
                    userdto.getUserdni(),
                    userdto.getUsername(),
                    userdto.getUserlastname(),
                    userdto.getUseremail(),
                    userdto.getUserphone(),
                    userdto.getUseravailability(),
                    userdto.getUservehicle());

            // Si se detectan datos en company, se generará un objeto de su clase, y se asociará con su donation.
        } else {
            company = new Company(
                    companyDto.getCocuit(),
                    companyDto.getConame(),
                    companyDto.getCocategory(),
                    companyDto.getCoemail(),
                    companyDto.getCophone(),
                    companyDto.getCoaddress(),
                    companyDto.getCocontactNm(),
                    companyDto.getCocontactLn());
        }
        Donation donacion = new Donation(
                dtoDonation.getDoncategory(),
                dtoDonation.getDonperishable(),
                dtoDonation.getDonexpiration(),
                dtoDonation.getDondetails(),
                user,
                company
        );
        donationS.save(donacion);
        return new ResponseEntity(new Message("Información guardada"), HttpStatus.OK);
    }}
