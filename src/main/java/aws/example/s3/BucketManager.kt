package aws.example.s3

import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.amazonaws.services.s3.model.Bucket
import java.util.*
import java.util.stream.Collectors

/**
 * Amazon S3 Bucket Manager.
 *
 * This code expects that you have AWS credentials set up per:
 * http://docs.aws.amazon.com/java-sdk/latest/developer-guide/setup-credentials.html
 */
class BucketManager(region: Regions = Regions.DEFAULT_REGION) {

    private var s3: AmazonS3 = AmazonS3ClientBuilder.standard()
            .withRegion(region)
            .build()

    fun findAllBucketNames(): List<String> {
        return s3.listBuckets()
                .stream()
                .map { b -> b.name }
                .collect(Collectors.toList())
    }

    fun findBucket(bucketName: String): Optional<Bucket> {
        return s3.listBuckets()
                .stream()
                .filter{ b -> b.name == bucketName }
                .findAny()
    }

    fun createBucket(bucketName: String): Bucket {
        if (s3.doesBucketExistV2(bucketName)) {
            println("Bucket $bucketName already exists!")
            return findBucket(bucketName).get()
        }

        return s3.createBucket(bucketName)
    }
}