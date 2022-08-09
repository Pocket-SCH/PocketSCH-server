package gdscsch.PocketSCHserver.bus.controller;

import gdscsch.PocketSCHserver.bus.dto.ImageFileDto;
import gdscsch.PocketSCHserver.bus.service.ImageFileService;
import gdscsch.PocketSCHserver.response.DefaultRes;
import gdscsch.PocketSCHserver.response.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequiredArgsConstructor
@RequestMapping("/pocket-sch/v1/bus")
public class ImageController {
    private final ImageFileService imageFileService;

    @PostMapping("/image/image-upload")
    public ResponseEntity write(@RequestParam("file") MultipartFile files, @RequestHeader("Authorization") String token) {
        try {
            String origFileName = files.getOriginalFilename();
            String fileName = token + ".jpg";

            String savePath = System.getProperty("user.dir") + "/images";

            if (!new File(savePath).exists()) {
                try{
                    new File(savePath).mkdir();
                }
                catch(Exception e){
                    e.getStackTrace();
                }
            }
            String filePath = savePath + "/" + fileName;
            files.transferTo(new File(filePath));

            imageFileService.saveFile(ImageFileDto.builder()
                    .tokenStr(token)
                    .origFilename(origFileName)
                    .fileName(fileName)
                    .filePath(filePath)
                    .build());
            String resMessage = "이미지 추가 완료";
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, resMessage), HttpStatus.OK);

        } catch(Exception e) {
            String resMessage = "이미지 추가 실패";
            return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, resMessage), HttpStatus.OK);
        }
    }

    @GetMapping(value = "/images/my-image", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> userSearch(@RequestHeader("Authorization") String token) throws IOException {
        InputStream imageStream = new FileInputStream("/home/ubuntu/pocket-sch/images/" + token + ".jpg");
        byte[] imageByteArray = imageStream.readAllBytes();
        imageStream.close();

        return imageByteArray.length == 0? new ResponseEntity(DefaultRes.res(StatusCode.OK, "이미지 호출 완료"), HttpStatus.OK) :
        new ResponseEntity(DefaultRes.res(StatusCode.OK, "이미지 없음"), HttpStatus.OK);
    }
}
