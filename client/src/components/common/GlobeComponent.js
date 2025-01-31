import React, { useRef, useMemo } from "react";
import { Canvas, useFrame } from "@react-three/fiber";
import { OrbitControls } from "@react-three/drei";
import * as THREE from "three";
import { useTheme } from "@mui/material";

const Globe = () => {
  const theme = useTheme();
  const meshRef = useRef();
  const groupRef = useRef();
  const isDarkMode = theme.palette.mode === "dark";

  // Optimize dot creation by using a single geometry and instancing
  const { dotPositions, connections } = useMemo(() => {
    const dots = [];
    const conns = [];
    const sphereRadius = 10;
    const dotsCount = 800;
    const connectionsCount = 50;

    // Create dots
    for (let i = 0; i < dotsCount; i++) {
      const phi = Math.random() * Math.PI * 2;
      const theta = Math.acos(2 * Math.random() - 1);
      const x = sphereRadius * Math.sin(theta) * Math.cos(phi);
      const y = sphereRadius * Math.sin(theta) * Math.sin(phi);
      const z = sphereRadius * Math.cos(theta);
      dots.push(x, y, z);
    }

    // Create connections
    for (let i = 0; i < connectionsCount; i++) {
      const startIndex = Math.floor(Math.random() * dotsCount) * 3;
      const endIndex = Math.floor(Math.random() * dotsCount) * 3;
      conns.push({
        start: new THREE.Vector3(
          dots[startIndex],
          dots[startIndex + 1],
          dots[startIndex + 2]
        ),
        end: new THREE.Vector3(
          dots[endIndex],
          dots[endIndex + 1],
          dots[endIndex + 2]
        ),
      });
    }

    return { dotPositions: dots, connections: conns };
  }, []);

  // Animation hook
  useFrame(() => {
    if (groupRef.current) {
      groupRef.current.rotation.y += 0.001;
    }
  });

  const dotGeometry = useMemo(() => new THREE.SphereGeometry(0.05, 8, 8), []);
  const dotMaterial = useMemo(
    () =>
      new THREE.MeshBasicMaterial({
        color: isDarkMode ? "#ffffff" : "#3b82f6",
        transparent: true,
        opacity: isDarkMode ? 0.8 : 0.6,
      }),
    [isDarkMode]
  );

  return (
    <group ref={groupRef}>
      {/* Base sphere */}
      <mesh ref={meshRef}>
        <sphereGeometry args={[10, 64, 64]} />
        <meshBasicMaterial
          color={isDarkMode ? "#1a1a1a" : "#e5e7eb"}
          transparent
          opacity={0.1}
          wireframe={true}
          wireframeLinewidth={0.5}
        />
      </mesh>

      {/* Dots */}
      {Array.from({ length: dotPositions.length / 3 }).map((_, i) => (
        <mesh
          key={i}
          geometry={dotGeometry}
          material={dotMaterial}
          position={[
            dotPositions[i * 3],
            dotPositions[i * 3 + 1],
            dotPositions[i * 3 + 2],
          ]}
        />
      ))}

      {/* Connections */}
      {connections.map((connection, index) => {
        const points = [connection.start, connection.end];
        const geometry = new THREE.BufferGeometry().setFromPoints(points);
        return (
          <line key={index} geometry={geometry}>
            <lineBasicMaterial
              color={isDarkMode ? "#ffffff" : "#3b82f6"}
              transparent
              opacity={0.1}
            />
          </line>
        );
      })}

      {/* Atmosphere */}
      <mesh>
        <sphereGeometry args={[10.2, 64, 64]} />
        <meshBasicMaterial
          color={isDarkMode ? "#ffffff" : "#3b82f6"}
          transparent
          opacity={0.05}
          side={THREE.BackSide}
        />
      </mesh>
    </group>
  );
};

const GlobeComponent = () => {
  const theme = useTheme();
  const isDarkMode = theme.palette.mode === "dark";

  return (
    <Canvas
      camera={{ position: [0, 0, 30], fov: 45 }}
      style={{
        background: "transparent",
        width: "100%",
        height: "100%",
      }}
      gl={{
        antialias: false,
        alpha: true,
        powerPreference: "default",
        preserveDrawingBuffer: false,
      }}
    >
      <ambientLight intensity={isDarkMode ? 0.3 : 0.2} />
      <pointLight position={[10, 10, 10]} intensity={isDarkMode ? 0.8 : 0.6} />
      <Globe />
      <OrbitControls
        enableZoom={false}
        autoRotate={true}
        autoRotateSpeed={0.5}
        enableDamping={true}
        dampingFactor={0.1}
      />
    </Canvas>
  );
};

export default GlobeComponent;
