package com.dts.recipeapp.services;

import com.dts.recipeapp.domain.Recipe;
import com.dts.recipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {
    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {

        this.recipeRepository = recipeRepository;
    }

    @Override
    public void saveImageFile(Long recipeId, MultipartFile file) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if (recipeOptional.isPresent()) {
            Recipe recipe = recipeOptional.get();
            try {
                Byte[] byteObjects = new Byte[file.getBytes().length];
                int i = 0;
                for (byte b :
                        file.getBytes()) {
                    byteObjects[i++] = b;
                }
                recipe.setImage(byteObjects);

                recipeRepository.save(recipe);

            } catch (IOException exception) {
                //todo handle it later
                log.error("Error occurred");
                exception.printStackTrace();
            }
        } else {
            log.error("Attempt to upload image for not existing recipe id: " + recipeId);
        }
    }
}
