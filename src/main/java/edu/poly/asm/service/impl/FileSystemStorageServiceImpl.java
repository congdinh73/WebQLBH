package edu.poly.asm.service.impl;

import edu.poly.asm.config.StorageProperties;
import edu.poly.asm.exception.StorageException;
import edu.poly.asm.exception.StorageFileNotFoundException;
import edu.poly.asm.service.StorageService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileSystemStorageServiceImpl implements StorageService {
    private final Path rootLocation;

    @Override
    public String getStoredFilename(MultipartFile file, String id) {
        String ext = FilenameUtils.getExtension(file.getOriginalFilename());
        return "p" + id + "." + ext;
    }

    public FileSystemStorageServiceImpl(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocaltion());

    }

    @Override
    public void store(MultipartFile file, String storedFilename) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("File is empty");
            }
            Path destination = this.rootLocation.resolve(Paths.get(storedFilename)).normalize().toAbsolutePath();

            if (!destination.getParent().equals(this.rootLocation.toAbsolutePath())) {
                throw new StorageException("Cannot store files into a different directory");
            }
            try(InputStream in = file.getInputStream()) {
                Files.copy(in, destination, StandardCopyOption.REPLACE_EXISTING);

            }
        } catch (Exception e) {
            throw new StorageException("Fail to stored file", e);
        }
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            org.springframework.core.io.Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            throw new StorageFileNotFoundException("Could not read file: " + filename);

        } catch (Exception e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename);
        }
    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public void delete(String storedFilename)  throws IOException {
        Path destination = this.rootLocation.resolve(Paths.get(storedFilename)).normalize().toAbsolutePath();

        Files.delete(destination);
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
            System.out.println(rootLocation.toString());

        } catch (Exception e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }

}
