package com.wd.userapplication.user;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void registerUser(User user)
    {
        userRepository.save(user);
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public User getUser(int userId)
    {

        Optional<User> user =userRepository.findById(userId);
        return user.get();
    }

    public void update(int userId,User user){

        user.setUserId(userId);

        userRepository.save(user);
    }

    public void deleteUser(int userId)
    {

        userRepository.deleteById(userId);
    }


    public void readFile(MultipartFile multipartFile) throws IOException {

        File file = new File("file");
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(multipartFile.getBytes());
        fos.close();

        FileInputStream excelFile = new FileInputStream(file);
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet dataTypeSheet = workbook.getSheetAt(0);

        Iterator<Row> iterator = dataTypeSheet.iterator();

        List<User> userList = new ArrayList<>();

        while ((iterator.hasNext())){

            User user = new User();

            DataFormatter dataFormatter = new DataFormatter();

            Row row = iterator.next();

            Iterator<Cell> cellIterator = row.iterator();

            if((cellIterator.hasNext())){

                Cell cell = cellIterator.next();

                user.setFirstName(dataFormatter.formatCellValue(cell));
            }

            if((cellIterator.hasNext())){

                Cell cell = cellIterator.next();

                user.setLastName(dataFormatter.formatCellValue(cell));
            }

            if((cellIterator.hasNext())){

                Cell cell = cellIterator.next();

                user.setEmailId(dataFormatter.formatCellValue(cell));
            }

            if((cellIterator.hasNext())){

                Cell cell = cellIterator.next();

                user.setPhoneNumber(dataFormatter.formatCellValue(cell));
            }

            if((cellIterator.hasNext())){

                Cell cell = cellIterator.next();

                user.setDob(dataFormatter.formatCellValue(cell));
            }

            if((cellIterator.hasNext())){

                Cell cell = cellIterator.next();

                user.setOccupation(dataFormatter.formatCellValue(cell));
            }
            userList.add(user);
        }

        for(User us: userList){
            userRepository.save(us);
        }

    }
}

