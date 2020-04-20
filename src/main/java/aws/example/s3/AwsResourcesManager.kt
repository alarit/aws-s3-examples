package aws.example.s3

import com.amazonaws.regions.Regions

fun main() {
    val bucketName = "alarit-my-custom-bucket"
    val bucketManager = BucketManager(Regions.EU_WEST_1)
    println("List all existing buckets...")
    bucketManager.findAllBucketNames()
            .forEachIndexed { index, n -> println("${index + 1} - $n") }

    println("Creating bucket $bucketName ...")
    bucketManager.createBucket(bucketName)
    println("Bucket $bucketName created!")

    println("Searching bucket $bucketName ...")
    if (bucketManager.findBucket(bucketName).isPresent) {
        println("Bucket $bucketName found!")
    } else {
        println("Bucket $bucketName not found!")
    }
}
