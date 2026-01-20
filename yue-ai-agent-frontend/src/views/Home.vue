<template>
  <div class="home-container">
    <div class="zen-background">
      <div class="soft-glow"></div>
    </div>

    <div class="main-wrapper">
      <header class="zen-header">
        <h1 class="main-heading">
          <span class="blob-shape">YUE-AI智能体</span>
        </h1>
      </header>

      <section class="selection-grid">
        <div class="organic-card" @click="navigateTo('/love-master')">
          <div class="card-tag">EMOTION</div>
          <div class="card-body">
            <h2 class="title">AI 恋爱大师</h2>
            <p class="summary">探讨亲密关系的边界，用算法解析感性的脉络。</p>
            <div class="visual-icon">
              <div class="blob-shape pink"></div>
            </div>
          </div>
          <div class="card-action">
            <span>开始对话</span>
            <svg class="arrow" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor">
              <path d="M7 17l9-9m0 0H8m8 0v8" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"/>
            </svg>
          </div>
        </div>

        <div class="organic-card" @click="navigateTo('/super-agent')">
          <div class="card-tag">LOGIC</div>
          <div class="card-body">
            <h2 class="title">AI 超级智能体</h2>
            <p class="summary">超越工具的定义，成为您逻辑思维的延伸。 </p>
            <div class="visual-icon">
              <div class="blob-shape blue"></div>
            </div>
          </div>
          <div class="card-action">
            <span>进入空间</span>
            <svg class="arrow" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor">
              <path d="M7 17l9-9m0 0H8m8 0v8" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"/>
            </svg>
          </div>
        </div>
      </section>

      <footer class="zen-footer">
        <div class="footer-line"></div>
        <AppFooter />
      </footer>
    </div>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'
import { useHead } from '@vueuse/head'
import AppFooter from '../components/AppFooter.vue'

useHead({
  title: 'YUE-AI | 极简智能',
  meta: [{ name: 'description', content: '探索极简 AI 交互体验' }]
})

const router = useRouter()
const navigateTo = (path) => {
  router.push(path)
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Noto+Serif+SC:wght@300;500&family=Quicksand:wght@300;500&display=swap');

.home-container {
  --bg-color: #fcfbf7; /* 米白色纸张感 */
  --text-primary: #2a2a2a;
  --text-secondary: #7a7a7a;
  --accent-pink: #eec9c9;
  --accent-blue: #c9d6ee;

  min-height: 100vh;
  background-color: var(--bg-color);
  color: var(--text-primary);
  font-family: 'Quicksand', 'Noto Serif SC', serif;
  position: relative;
  overflow-x: hidden;
}

.zen-background {
  position: fixed;
  inset: 0;
  z-index: 0;
}

.soft-glow {
  position: absolute;
  top: 10%;
  left: 50%;
  transform: translateX(-50%);
  width: 80vw;
  height: 80vw;
  background: radial-gradient(circle, rgba(238, 201, 201, 0.2) 0%, rgba(201, 214, 238, 0.1) 50%, transparent 100%);
  filter: blur(60px);
}

.main-wrapper {
  position: relative;
  z-index: 1;
  max-width: 1100px;
  margin: 0 auto;
  padding: 80px 24px;
  display: flex;
  flex-direction: column;
  min-height: 100vh;
}

/* 头部样式 */
.zen-header {
  margin-bottom: 100px;
}

.origin-mark {
  font-size: 0.75rem;
  letter-spacing: 5px;
  color: var(--text-secondary);
  margin-bottom: 24px;
}

.main-heading {
  font-size: clamp(2.5rem, 6vw, 4rem);
  font-weight: 500;
  margin-bottom: 30px;
  letter-spacing: -1px;
}

.main-heading .thin {
  font-weight: 300;
  font-family: 'Noto Serif SC', serif;
  font-style: italic;
  margin-left: 10px;
}

.description {
  max-width: 500px;
  line-height: 1.8;
  color: var(--text-secondary);
  font-size: 1.1rem;
}

/* 卡片设计 */
.selection-grid {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 40px;
  margin-bottom: 60px;
}

.organic-card {
  background: #fff;
  padding: 40px;
  border-radius: 2px; /* 极小圆角更具工业设计感 */
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  min-height: 380px;
  cursor: pointer;
  transition: all 0.6s cubic-bezier(0.19, 1, 0.22, 1);
  border: 1px solid rgba(0,0,0,0.03);
  position: relative;
}

.organic-card:hover {
  transform: translateY(-8px);
  box-shadow: 0 20px 40px rgba(0,0,0,0.02);
  border-color: rgba(0,0,0,0.1);
}

.card-tag {
  font-size: 0.7rem;
  letter-spacing: 2px;
  color: var(--text-secondary);
  margin-bottom: 40px;
}

.title {
  font-size: 1.8rem;
  font-weight: 500;
  margin-bottom: 15px;
}

.summary {
  color: var(--text-secondary);
  line-height: 1.6;
}

.visual-icon {
  margin-top: 40px;
  height: 60px;
  display: flex;
  align-items: center;
}

.blob-shape {
  width: 40px;
  height: 40px;
  border-radius: 40% 60% 70% 30% / 40% 50% 60% 50%;
  animation: blob-morph 8s infinite alternate ease-in-out;
}

.pink { background-color: var(--accent-pink); }
.blue { background-color: var(--accent-blue); }

@keyframes blob-morph {
  0% { border-radius: 40% 60% 70% 30% / 40% 50% 60% 50%; }
  100% { border-radius: 60% 40% 30% 70% / 50% 60% 40% 60%; }
}

.card-action {
  margin-top: 40px;
  display: flex;
  align-items: center;
  gap: 10px;
  font-weight: 500;
  font-size: 0.9rem;
}

.arrow {
  transition: transform 0.3s ease;
}

.organic-card:hover .arrow {
  transform: translate(3px, -3px);
}

/* 页脚调整 */
.zen-footer {
  margin-top: auto;
  padding-top: 40px;
}

.footer-line {
  height: 1px;
  background-color: rgba(0,0,0,0.05);
  margin-bottom: 20px;
}

/* 响应式适配 */
@media (max-width: 850px) {
  .selection-grid { grid-template-columns: 1fr; }
  .main-heading { font-size: 2.5rem; }
}
</style>