# Photo OCR

The pipline of photo OCR is:

![Photo OCR](images/photo_OCR_pipeline.png)

## Sliding windows

Supervised learning for pedestrian detection

![pedestrian_example.png](images/pedestrian_example.png)

**Photo OCR pipeline**:

![pipeline](images/pipeline_details.png)

## Discussion on getting more data

1. Make sure you have a low bias classifier before expanding the effort. (Plot learning curves).  E.g. keep increasing the number of features/number of hidden units in neural network until you have a low bias classifier.

2. "How much work would it be to get 10x as much data as we currently have?"
    - Artificial data synthesis
    - Collect/label it yourself (how many hours?)
    - "Crowd source" (e.g. Amazon Mechanical Turk)

## Ceiling Analysis

Here we use OCR example to show where to improve the accuracy:

![Analysis](images/Estimating_the_errors_due_to_each_component.png)

![Facial detection](images/example_facial_detection.png)


![Error](error.png)