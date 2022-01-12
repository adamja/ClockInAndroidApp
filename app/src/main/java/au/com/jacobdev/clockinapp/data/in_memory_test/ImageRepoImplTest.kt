package au.com.jacobdev.clockinapp.data.in_memory_test

import au.com.jacobdev.clockinapp.data.dao.ImageDao
import au.com.jacobdev.clockinapp.data.repo.ImageRepo
import au.com.jacobdev.clockinapp.data.model.Image
import au.com.jacobdev.clockinapp.data.in_memory_test.TestDataRepo.IMAGES
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ImageRepoImplTest(
    private val dao: ImageDao
) : ImageRepo {

    override suspend fun insertImage(image: Image) {
        IMAGES = IMAGES + image
    }

    override suspend fun updateImage(image: Image) {
        IMAGES = IMAGES.map {
            if (image.id == it.id) image else it
        }
    }

    override suspend fun deleteImage(image: Image) {
        IMAGES = IMAGES.filter {
            image.id != it.id
        }
    }

    override suspend fun getImageById(id: String): Image? {

        return IMAGES.find { it.id == id }
    }

    override fun getImages(): Flow<List<Image>> {
        return flow { emit(IMAGES) }
    }

}