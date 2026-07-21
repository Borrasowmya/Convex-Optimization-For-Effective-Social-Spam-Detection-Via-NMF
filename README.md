# Convex Optimization for Effective Social Spam Detection via NMF

## Overview

This project presents a Machine Learning approach for detecting social spam and fake Twitter users using Non-negative Matrix Factorization (NMF) and Random Forest Classification. The system extracts meaningful features from Twitter data and classifies accounts as spam or legitimate users.

---

## Features

- Detects spam and fake Twitter users
- Uses Non-negative Matrix Factorization (NMF) for feature extraction
- Random Forest classifier for prediction
- WEKA-based machine learning implementation
- Java desktop application
- ARFF dataset support

---

## Technologies Used

- Java
- WEKA
- Random Forest
- Non-negative Matrix Factorization (NMF)
- Machine Learning
- Twitter Dataset

---

## Project Structure

```
Project/
│
├── Main.java
├── SpamFilter.java
├── RandomForestClassifier.java
├── ReadDataset.java
├── ViewDetection.java
├── Tweet.java
├── Word.java
├── data.arff
├── weka.jar
├── json-simple-1.1.jar
├── README.md
```

---

## Dataset

The project uses a Twitter dataset stored in **ARFF** format for training and testing.

Dataset File:

```
data.arff
```

---

## Algorithm

1. Load Twitter dataset
2. Preprocess tweets
3. Extract features using NMF
4. Train Random Forest classifier
5. Detect spam or genuine users
6. Display prediction results

---

## How to Run

### Requirements

- Java JDK 8+
- WEKA Library
- IDE (NetBeans / Eclipse / IntelliJ)

### Steps

1. Clone the repository

```
git clone https://github.com/Borrasowmya/Convex-Optimization-For-Effective-Social-Spam-Detection-Via-NMF.git
```

2. Open the project in your IDE.

3. Add the following libraries:

- weka.jar
- json-simple-1.1.jar

4. Run

```
Main.java
```

---

## Output

The application analyzes Twitter data and predicts whether a user is:

- Spam User
- Genuine User

---

## Future Improvements

- Deep Learning models
- Real-time Twitter API integration
- Web-based deployment
- Higher detection accuracy
- Larger datasets

---

## Author

**Sowmya Borra**

GitHub:
https://github.com/Borrasowmya

---

## Repository

https://github.com/Borrasowmya/Convex-Optimization-For-Effective-Social-Spam-Detection-Via-NMF

