<script setup lang="ts">
import CustomButton from '@/components/button/CustomButton.vue'
</script>

<template>
  <main>
    <div class="cover">
      <section class="hero">
        <div class="hero-content">
          <h1>환영합니다!</h1>
          <p>현이의 가방끈은 엄선된 책과 열정적인 스터디를 제공합니다.</p>
          <CustomButton label="자세히 보기" route="#about" />
        </div>
      </section>

      <!-- 소개 섹션 -->
      <section id="about" class="about"></section>

      <!-- 서비스 섹션 -->
      <section id="services" class="services">
        <h2>신간 도서</h2>
        <div class="services-container">
          <!-- <div class="service-item" v-for="item in introBook">
            <img :src="item.image" alt="서비스 1" @click="fnView(item.bookId)" />
          </div> -->
        </div>
      </section>

      <!-- 갤러리 섹션 -->
      <section id="gallery" class="gallery">
        <div class="split-container">
          <div class="split left">
            <h2>북스토어</h2>
            <a href="/shop" class="split-link"></a>
          </div>
          <div class="split right">
            <h2>스터디그룹</h2>
            <a href="/study" class="split-link"></a>
          </div>
        </div>
      </section>
    </div>
  </main>
</template>
<style scoped>
main {
  line-height: 1.6;
  width: 100vw;
  margin: 0px;
  background-image: url('/images/intro.jpg');
  background-size: cover;
  background-repeat: no-repeat;
  background-attachment: fixed;
}
.cover {
  display: block;
  width: 100%;
  height: 100%;
  min-height: 100vh;
  opacity: 0.9;
  background-color: var(--color-text-inverse);
}

/* 히어로 섹션 */
.hero {
  position: relative;
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;
  padding: 0 20px;
}

.hero-content h1 {
  font-size: 3em;
  margin-bottom: 20px;
  color: var(--color-text-soft);
}

.hero-content p {
  font-size: 1.5em;
  margin-bottom: 30px;
  color: var(--color-text);
}

/* 소개 섹션 */
.about {
  padding-top: 160px;
  text-align: center;
  background-color: var(--color-text-inverse);
  height: 200px;
}

.about h2 {
  font-size: 2.5em;
  margin-bottom: 20px;
  color: var(--color-text-soft);
}

.about p {
  max-width: 800px;
  margin: 0 auto;
  font-size: 1.5em;
  color: var(--color-text-soft);
}

/* 서비스 섹션 */
.services {
  padding: 0px 20px;
  text-align: center;
  background-color: var(--color-text-inverse);
  height: 40%;
}

.services h2 {
  font-size: 2.5em;
  margin-bottom: 20px;
  color: var(--color-text-soft);
}

.services-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  gap: 20px;
}

.service-item {
  background-color: var(--color-text-inverse);
  border: 1px solid #ddd;
  border-radius: 10px;
  width: 300px;
  padding: 20px;
  transition:
    transform 0.3s ease,
    box-shadow 0.3s ease;
}

.service-item:hover {
  transform: translateY(-10px);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.service-item img {
  width: 100%;
  border-radius: 10px;
  margin-bottom: 15px;
}

.service-item h3 {
  margin-bottom: 10px;
  font-size: 1.5em;
  color: #e74c3c;
}

.service-item p {
  font-size: 1em;
  color: var(--color-text-soft);
}

/* 갤러리 섹션 */
.gallery {
  padding: 60px 20px;
  text-align: center;
  background-color: var(--color-text-inverse);
  height: 10%;
}

.gallery h2 {
  font-size: 2.5em;
  margin-bottom: 40px;
  color: var(--color-text-soft);
}

.gallery-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  gap: 15px;
}

.gallery-container img {
  width: 100px;
  height: 100px;
  object-fit: cover;
  border-radius: 10px;
  transition:
    transform 0.3s ease,
    box-shadow 0.3s ease;
}

.gallery-container img:hover {
  transform: scale(1.05);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}

.split-container {
  position: relative;
  width: 100%;
  height: 20vh;
  display: flex;
}

.split {
  position: relative;
  flex: 1;
  overflow: hidden;
  transition: flex 0.5s ease;
}

.split.left {
  background-size: cover;
}

.split.right {
  background-size: cover;
}

.split h2 {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  color: var(--color-text-soft);
  font-size: 3em;
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.7);
  opacity: 0;
  transition: opacity 0.5s ease;
}

.split-link {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  text-decoration: none;
}

.split:hover {
  flex: 1.5;
}

.split:hover h2 {
  opacity: 1;
}

.split-container::before {
  content: '';
  position: absolute;
  top: 0;
  bottom: 0;
  left: calc(50% - 1px);
  width: 2px;
  background-color: var(--color-text-inverse);
  z-index: 2;
}
</style>
